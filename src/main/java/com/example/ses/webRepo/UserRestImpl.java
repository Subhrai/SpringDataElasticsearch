package com.example.ses.webRepo;

import com.example.ses.service.UserServiceImpl;
import com.example.ses.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;

@RestController
@RequestMapping("/")
public class UserRestImpl {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //autowired ES template using this you can directly hit query on ES
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateStudent(@RequestBody User user){
        User resUser = userServiceImpl.save(user);
        return new ResponseEntity<User>(resUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ResponseEntity<?> getListUser() {
        return new ResponseEntity<>(userServiceImpl.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/roleList", method = RequestMethod.GET)
    public ResponseEntity<?> getListUserBasedRole(@RequestParam("role") String role) {
        return new ResponseEntity<>(userServiceImpl.findByRole(role), HttpStatus.OK);
    }

    @RequestMapping(value = "/NamePassList", method = RequestMethod.GET)
    public ResponseEntity<?> getListUsernameAndPassword(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord) {
        return new ResponseEntity<>(userServiceImpl.userRepository.findByUserNameAndPassWordAllIgnoreCase(userName,passWord), HttpStatus.OK);
    }

    /**
     * Example of hit query using ESTemplate it will return list of users.
     * @return
     */
    @RequestMapping(value = "/listquery", method = RequestMethod.GET)
    public ResponseEntity<?> getListByQuery() {
        SearchQuery  searchQuery = new NativeSearchQueryBuilder().withFilter(regexpQuery("userName", ".*")).build();
        return new ResponseEntity<>(elasticsearchTemplate.queryForList(searchQuery, User.class), HttpStatus.OK);
    }
}
