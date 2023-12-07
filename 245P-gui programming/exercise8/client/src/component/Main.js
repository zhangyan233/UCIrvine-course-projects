import "./Main.css";
import informationurl from './information.jpg';
import backend from './backend.jpg';
import formulate from './graduate assistant.jpg';

function Main() {
    return (
        <>
         <div className="main">
            <div className="inAddEducation">
                <div className="information">
                    <article>
                        <figure>
                            <img className="personal photo" src={informationurl} alt="personal photo"  width="180" height="220"/>
                        </figure>

                        <hgroup>
                            <h2>Yan's information</h2>
                            <h3>Major: Software Engineering</h3>
                            <h3>Graduate Year: Dec 2024</h3>
                        </hgroup>

                        <p>I am Yan, and actively looking for a 2024 summer SDE internship. I am pursuing master degree of Software Engineering and have received a master's degree and a bachelor's degree in electrical engineering in Huazhong University of Science and Technology and have a back-end software development engineer internship experience in China.</p>

                    </article> 
                </div>

                <div className="experience">
                    <h2>Yan's experience</h2>

                    <article>
                        <h3>Backend Software Intern</h3>
                        <figure>
                            <img className="backend intern" src={backend} alt="backend intern" width="240" height="130"/>
                        </figure>
                        <p>Participated in the product design; Developed some back-end modules.</p>
                    </article>

                    <article>
                        <h3>Graduate Research Assistant</h3>
                        <figure>
                            <img className="assistant" src={formulate} alt="graudate assistant" width="240" height="130"/>
                        </figure>
                        <p>Established the working circuit simulation model of the voltage transformer and physical simulation model of the fuse.</p>
                    </article>
                </div>
            </div>


            <div className="eduAddContact">
                <div className="education">
                    <h2>Education experience</h2>
                        <a href="">Software Engineering, University of California, Irvine</a>
                        <a href="">Electrical Engineering, Huazhong University of Science and Technology</a>
                        <a href="">Electrical Engineering, Huazhong University of Science and Technology</a>
                </div>
   
   
                <div className="contact-details">
                    <h2>Contact</h2>
                    <p>mail address: yanz73@uci.edu</p>
                    <p>address: XXXX Palo Verde, Irvine, CA92617</p>
                    </div>

 </div>


     </div>
        </>
      );
}

export default Main;
