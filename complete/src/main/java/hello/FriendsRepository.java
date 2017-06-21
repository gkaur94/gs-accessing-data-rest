package hello;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Gagan on 21/06/2017.
 */

public interface FriendsRepository extends PagingAndSortingRepository<Friendship, Long> {

}
