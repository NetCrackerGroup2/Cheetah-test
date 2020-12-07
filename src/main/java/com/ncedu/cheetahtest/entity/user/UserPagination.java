package com.ncedu.cheetahtest.entity.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserPagination {
    private List<User> page;
    private int numElem;

    public UserPagination(List<User> allUsers, int size, int numPage){
        List<User> res;
        if (size * numPage >= allUsers.size()) {
            res = new ArrayList<>(allUsers.subList(size * (numPage - 1), allUsers.size()));
        } else {
            res = new ArrayList<>(allUsers.subList(size * (numPage - 1), size * numPage));
        }
        this.page = res;
        this.numElem = allUsers.size();
    }

}
