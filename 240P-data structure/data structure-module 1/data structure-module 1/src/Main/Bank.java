package Main;

import Entity.AvailableID;
import Entity.User;


import static java.lang.Math.max;
import static java.lang.Math.min;

// bank class
public class Bank {
    private Integer newId;//The minimum Id that has not been assigned
    private Integer size;// the size of bank system
    private User userHead;// The head of the account list
    private AvailableID availableID;// The first value of the availableID list

    //constructor,The initial parameter settings of the bank system
    public Bank() {
        this.newId = 1;//Minimum account ID that has never been assigned
        this.size = 0;
        this.userHead = null;
        this.availableID = null;
    }

    //getter and setter
    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public User getUserHead() {
        return userHead;
    }

    public void setUserHead(User userHead) {
        this.userHead = userHead;
    }

    public AvailableID getAvailableID() {
        return availableID;
    }

    public void setAvailableID(AvailableID availableID) {
        this.availableID = availableID;
    }

    public void addUser(User user) {
        User newUser = user;

        //unique ID re-assigned to new users.
        if (availableID != null) {
            //use the first of Available ID, and delete it from availableID list
            newUser.setId((availableID.getID()));
            AvailableID temp = availableID;
            availableID = temp.getNext();
            temp = null;
        } else {
            //no availableID, so assign newID to user
            newUser.setId(newId++);
            newUser.setNext(null);
        }

        //if userHead is null, assign newUser to userHead
        if (userHead == null) {
            userHead = newUser;
        } else {
            //if userHead isn't null, need to find the location of ID
            User target = userHead;
            while (target.getNext() != null && target.getNext().getId() < newUser.getId()) {
                target = target.getNext();
            }

            //judge whether target is the end of list
            if (target.getNext() != null) {
                //if not, assign target'next to the newId'next
                newUser.setNext(target.getNext());
            }
            //insert newID after target
            target.setNext(newUser);
        }

        size++;
    }

    public void deleteUser(Integer id) {
        User temp = userHead;

        //find the location of id
        while (temp.getNext() != null && temp.getNext().getId() < id) {
            temp = temp.getNext();
        }

        //judge whether the id exists in linked list, if not, return
        if (temp.getNext() == null || (temp.getNext() != null && temp.getNext().getId() > id)) {
            return;
        }

        //if id exists, judge whether temp->next is the end of linked list ?yes: delete the end of list
        if (temp.getNext().getNext() == null) {
            temp.setNext(null);
        } else {
            //if temp is not the end of list, assign the deleted user'next to temp
            User target = temp.getNext();
            temp.setNext(target.getNext());
            target = null;
        }

        //put this ID in the list of AvailableList
        AvailableID newAvailableId = new AvailableID(id);
        if (availableID == null) {
            availableID = newAvailableId;
        } else {
            AvailableID start = availableID;
            while (start.getNext() != null) {
                start = start.getNext();
            }
            start.setNext(newAvailableId);
            newAvailableId.setNext(null);
        }

        size--;
    }

    public void payUserToUser(Integer payerID, Integer payeeID, Integer amount) {
        User payer = userHead;

        //find the position of payerID
        while (payer != null && payer.getId() != payerID) {
            payer = payer.getNext();
        }

        //judge whether payerID exists in bank system,if not, return
        if (payer == null) {
            return;
        }

        //Judge whether the initialAmount of payerId is larger than amount
        //yes, it can pay amount
        //no, it cannot be paid
        if (payer.getInitialAmount() - amount < 0) {
            return;
        }

        //find the position of payeeID
        User payee = userHead;
        while (payee != null && payee.getId() != payeeID) {
            payee = payee.getNext();
        }

        //judge whether payerID exists in bank system,if not, return
        if (payee == null) {
            return;
        }

        //payer pay money, and payee receive money
        payer.setInitialAmount(payer.getInitialAmount() - amount);
        payee.setInitialAmount(payee.getInitialAmount() + amount);
    }

    public Integer getMedianID() {
        //find the mid position of bank system
        Integer target = (size + 1) / 2;
        User start = userHead;
        while (target != 1) {
            start = start.getNext();
            target--;
        }
        return start.getId();
    }

    public void mergeAccounts(Integer ID1, Integer ID2) {
        User temp = userHead;
        User one;
        User two;

        //find the position of ID1
        while (temp != null && temp.getId() != ID1) {
            temp = temp.getNext();
        }

        //judge whether ID1 exists in the bank system, if not, return
        if (temp == null) {
            return;
        }
        one = temp;

        temp = userHead;
        //find the position of ID2
        while (temp != null && temp.getId() != ID2) {
            temp = temp.getNext();
        }

        //judge whether ID2 exists in bank system
        if (temp == null) {
            return;
        }

        //whether ID1 and ID2 have the same information about user
        //yes: mergeAccount
        //no:return without process
        if (temp.getName() != one.getName() || temp.getSSN() != one.getSSN() || temp.getAddress() != one.getAddress()) {
            return;
        }
        two = temp;

        //find the minID to merge, maxID to delete
        if (one.getId() < two.getId()) {
            one.setInitialAmount(one.getInitialAmount() + two.getInitialAmount());
            this.deleteUser(two.getId());
        } else {
            two.setInitialAmount(one.getInitialAmount() + two.getInitialAmount());
            this.deleteUser(one.getId());
        }
    }

    public Bank mergeBanks(Bank OrangeCounty, Bank LosAngeles) {
        Bank SouthernCalifornia = new Bank();
        User start = null;//start user of new bank system
        User end = null;//end user of new bank system
        User temp;//add to new bank system every time

        //whether one of link is null? if not, compare the headID of two lists,and choose the smaller one to new bank system
        while (OrangeCounty.getUserHead() != null && LosAngeles.getUserHead() != null) {
            Integer startOrangeID = OrangeCounty.getUserHead().getId();
            Integer startLosAngeles = LosAngeles.getUserHead().getId();

            if (startOrangeID < startLosAngeles) {
                User erase = OrangeCounty.getUserHead();
                temp = erase;
                OrangeCounty.setUserHead(erase.getNext());
                erase = null;
            } else if (startOrangeID > startLosAngeles) {
                User erase = LosAngeles.getUserHead();
                temp = erase;
                LosAngeles.setUserHead(erase.getNext());
                erase = null;
            } else {
                //Aec order, if ID of two bank are the same, delete the repeat one from losAngeles, and add it to list again
                User erase = OrangeCounty.getUserHead();
                temp = erase;
                OrangeCounty.setUserHead(erase.getNext());
                erase = null;

                User repeatUserID = LosAngeles.getUserHead();
                LosAngeles.setUserHead(repeatUserID.getNext());
                LosAngeles.addUser(repeatUserID);
                repeatUserID = null;
            }

            //whether the new bank list is empty?
            if (start == null) {
                //if yes, temp is start of new bank system
                start = temp;
            } else {
                //if not, add temp to end of list
                end.setNext(temp);
            }
            //update the end of bank list
            end = temp;
        }

        //if one of bank exists users, add them to the end of new bank
        User remain = OrangeCounty.getUserHead() == null ? LosAngeles.getUserHead() : OrangeCounty.getUserHead();
        end.setNext(remain);
        SouthernCalifornia.setUserHead(start);
        return SouthernCalifornia;
    }


}