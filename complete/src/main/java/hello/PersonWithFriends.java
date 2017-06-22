package hello;

import java.util.List;

/**
 * Created by Gagan on 22/06/2017.
 */
public class PersonWithFriends {
    private Person person;
    private List<Person> friends;
    private List<Person> friendsOfFriends;

    public PersonWithFriends(Person person, List<Person> friends, List<Person> friendsOfFriends) {
        this.person = person;
        this.friends = friends;
        this.friendsOfFriends = friendsOfFriends;
    }

    public Person getPerson() {
        return person;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public List<Person> getFriendsOfFriends() {
        return friendsOfFriends;
    }
}
