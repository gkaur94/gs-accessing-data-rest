package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friendship {

    public long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long friendId1;

    private long friendId2;

    public long getFriendId1() {
        return friendId1;
    }

    public void setFriendId1(long friendId1) {
        this.friendId1 = friendId1;
    }

    public long getFriendId2() {
        return friendId2;
    }

    public void setFriendId2(long friendId2) {
        this.friendId2 = friendId2;
    }
}
