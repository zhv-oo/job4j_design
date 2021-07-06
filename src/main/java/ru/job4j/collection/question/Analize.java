package ru.job4j.collection.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, User> tmpMap = previous.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        for (User user : current) {
            if (tmpMap.get(user.getId()) == null) {
                rsl.setAdded(rsl.getAdded() + 1);
            }
            if (tmpMap.containsKey(user.getId())
                    && !tmpMap.get(user.getId()).getName().equals(user.getName())) {
                rsl.setChanged(rsl.getChanged() + 1);
            }
        }
        rsl.setDeleted(previous.size() + rsl.getAdded() - current.size());
        return rsl;
    }
}