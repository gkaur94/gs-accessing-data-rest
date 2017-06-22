package hello;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Gagan on 21/06/2017.
 */

public interface FriendsRepository extends PagingAndSortingRepository<Friendship, Long> {

    public List<Friendship> findByFriendId1(Long f1);
    public List<Friendship> findByFriendId2(Long f2);

}
