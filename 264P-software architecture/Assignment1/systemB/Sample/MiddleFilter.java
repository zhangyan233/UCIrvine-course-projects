import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/******************************************************************************************************************
* File:MiddleFilter.java
* Project: Lab 1
* Copyright:
*   Copyright (c) 2020 University of California, Irvine
*   Copyright (c) 2003 Carnegie Mellon University
* Versions:
*   1.1 January 2020 - Revision for SWE 264P: Distributed Software Architecture, Winter 2020, UC Irvine.
*   1.0 November 2008 - Sample Pipe and Filter code (ajl).
*
* Description:
* This class serves as an example for how to use the FilterRemplate to create a standard filter. This particular
* example is a simple "pass-through" filter that reads data from the filter's input port and writes data out the
* filter's output port.
* Parameters: None
* Internal Methods: None
******************************************************************************************************************/

public class MiddleFilter extends FilterFramework
{
	public static double old_altitude1=-1.0D;
	public static double Old_altitude2=-1.0D;
	public static boolean wildJump;
	private static ArrayList<String> information = new ArrayList();

	public void run()
    {
		int bytesread = 0;					// Number of bytes read from the input file.
		int byteswritten = 0;				// Number of bytes written to the stream.
		byte databyte = 0;					// The byte of data read from the file
		Calendar TimeStamp = Calendar.getInstance();
		SimpleDateFormat TimeStampFormat = new SimpleDateFormat("yyyy MM dd::hh:mm:ss:SSS");
		int MeasurementLength = 8;
		int IdLength = 4;
		double velocity = 0.0D;
		double altitude = 0.0D;
		double pressure = 0.0D;
		double temperature = 0.0D;
		String firstLine = "Time,Velocity,Altitude,Pressure,Temperature";
		information.add(firstLine);

		// Next we write a message to the terminal to let the world know we are alive...
		System.out.print( "\n" + this.getName() + "::Middle Reading ");

		while (true)
		{
			// Here we read a byte and write a byte
			try
			{
				int id = 0;

				for(int i = 0; i < IdLength; ++i) {
					databyte = this.ReadFilterInputPort();
					id |= databyte & 255;
					if (i != IdLength - 1) {
						id <<= 8;
					}

					++bytesread;
					WriteFilterOutputPort(databyte);
				}

				long measurement = 0L;

				for(int i = 0; i < MeasurementLength; ++i) {
					databyte = this.ReadFilterInputPort();
					measurement |= (long)(databyte & 255);
					if (i != MeasurementLength - 1) {
						measurement <<= 8;
					}

					++bytesread;
					//judge whether this data is altitude
					if(id!=2){
						this.WriteFilterOutputPort(databyte);
					}
				}

				if (id == 0) {
					TimeStamp.setTimeInMillis(measurement);
				} else if (id == 1) {
					velocity = Double.longBitsToDouble(measurement);
				} else if (id == 3) {
					pressure = Double.longBitsToDouble(measurement);
				} else if (id == 4) {
					temperature = Double.longBitsToDouble(measurement);
				} else {
					altitude=Double.longBitsToDouble(measurement);
					if(old_altitude1!=-1.0D&&Math.abs(altitude-old_altitude1)>100.0D){
						wildJump=true;
						//transfer all data from wildJump to string and put them in information
						String data=TimeStampFormat.format(TimeStamp.getTime())+","+String.format("%3.5f",velocity)+","+String.format("%3.5f",altitude)+","
								+String.format("%3.5f",pressure)+","+String.format("%3.5f",temperature);
						information.add(data);
					}

					//if wildJump is true, replace original altitude to either former one or average of former two
					if(wildJump){
						if(Old_altitude2==-1.0D){
							altitude=old_altitude1;
						}else{
							altitude=(old_altitude1+Old_altitude2)/2.0D;
						}
					}

					//transfer double altitude to 8 bytes data
					ByteBuffer bb=ByteBuffer.allocate(8);
					bb.putLong(Double.doubleToLongBits(altitude));
					byte[] array = bb.array();

					for(int i=0;i<MeasurementLength;i++){
						databyte=array[i];
						WriteFilterOutputPort(databyte);
					}

					//attach a signal to inform sinkFilter which altitude has been replaced
					if(wildJump){
						WriteFilterOutputPort((byte) 1);
					}else{
						WriteFilterOutputPort((byte) 0);
					}

					//update old altitude
					Old_altitude2=old_altitude1;
					old_altitude1=altitude;
					wildJump=false;

				}

				System.out.print("\n");
			}
			catch (EndOfStreamException e)
			{
				ClosePorts();
				output(information);
				System.out.print( "\n" + this.getName() + "::Middle Exiting; bytes read: " + bytesread + " bytes written: " + byteswritten );
				break;
			}
		}
   }

	public static void output(ArrayList<String> information){
		BufferedWriter bw=null;
		try {
			bw=new BufferedWriter(new FileWriter("Wildpoints.csv"));

			for (String s : information) {
				bw.write(s);
				bw.flush();
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}