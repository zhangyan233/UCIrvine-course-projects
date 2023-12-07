package Test;
import Entity.User;
import Main.Bank;

public class TestModule {
    public static void main(String[] args) {
        User userA=new User("Tom","orange","2260",1000);
        User userB=new User("Amy","orange","1234",5000);
        User userC=new User("Lisa","orange","2532",10000);
        User userD=new User("Wilson","santa clara","8273",2000);
        User userE=new User("Tom","orange","2260",7700);
        User userF=new User("Alisa","losAngles","7751",8000);
        User userG=new User("Wendy","santa clara","9890",100);
        User userH=new User("Victor","losAngles","2200",560);
        User userI=new User("Kris","santa clara","1333",6700);

        Bank orange=new Bank();

        //test addUser()
        orange.addUser(userA);
        orange.addUser(userB);
        orange.addUser(userC);
        orange.addUser(userD);
        orange.addUser(userE);
        orange.addUser(userF);
        orange.addUser(userG);
        orange.addUser(userH);
        orange.addUser(userI);

        User temp=orange.getUserHead();
        while(temp!=null){
            System.out.println(temp.getId()+" "+temp.getName()+" "+temp.getInitialAmount());
            temp=temp.getNext();
        }

        //test deleteID()
//        orange.deleteUser(4);//1.id exists in bank system
//        orange.deleteUser(10); //2. id doesn't exist in bank system


        //test payUserToUser
//        orange.payUserToUser(3,4,2000);//1.payerID or payeeID doesn't exist
//        orange.payUserToUser(10,1,2000);

//        orange.payUserToUser(1,2,2000);//2.payerID doesn't have enough money

//        orange.payUserToUser(3,1,2000);//3.correct situation

        //test getMedianId()
//        orange.addUser(new User("Albert","san deigo","1235",20));
//        orange.addUser(new User("Jack","san deigo","2945",1080));
//        Integer medianId=orange.getMedianID();
//        System.out.println(medianId);

        //test mergeId
//        orange.mergeAccounts(1,10);//1.id1 or id2 doesn't exist in bank system
//        orange.mergeAccounts(29,6);
//
//        orange.mergeAccounts(1,2);//2. id1 and id2 don't have the same information
//
//        orange.mergeAccounts(1,5);//3. correct situation
//
//        temp=orange.getUserHead();
//        while(temp!=null){
//            System.out.println(temp.getId()+" "+temp.getName()+" "+temp.getInitialAmount());
//            temp=temp.getNext();
//        }

//        //test mergeBank
//        User userO=new User("luna","Los Angles","1112",6000);
//        User userM=new User("natasha","Los Angles","7812",7000);
//        User userN=new User("lyssia","Los Angles","6283",6000);
//        User userP=new User("wenny","Los Angles","2934",3000);
//        User userQ=new User("Sandy","Los Angles","7399",12000);
//        User userR=new User("Jeff","Los Angles","5764",2500);
//        User userS=new User("willam","Los Angles","4934",6000);
//
//        Bank LosAngles=new Bank();
//        LosAngles.addUser(userO);
//        LosAngles.addUser(userM);
//        LosAngles.addUser(userN);
//        LosAngles.addUser(userP);
//        LosAngles.addUser(userQ);
//        LosAngles.addUser(userR);
//        LosAngles.addUser(userS);
//
//        User tool=LosAngles.getUserHead();
//        while(tool!=null){
//            System.out.println(tool.getId()+" "+tool.getName()+" "+tool.getInitialAmount());
//            tool=tool.getNext();
//        }
//
//        Bank SouthCalifornia=orange.mergeBanks(orange,LosAngles);
//        temp=SouthCalifornia.getUserHead();
//        while(temp!=null){
//            System.out.println(temp.getId()+" "+temp.getName()+" "+temp.getInitialAmount());
//            temp=temp.getNext();
//        }
    }
}
