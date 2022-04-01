package com.murdered.cinema.util;

import com.murdered.cinema.dto.SessionDTO;
import com.murdered.cinema.model.Session;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SessionSortUtil {

    public static void SortByAvailablePlaces(List<SessionDTO> list){
        Collections.sort(list, sessionComparator);
    }

    public static Comparator<SessionDTO> sessionComparator = new Comparator<SessionDTO>(){

        @Override
        public int compare(SessionDTO o1, SessionDTO o2) {
            int place1 = o1.getAvailablePlaces();
            int place2 = o2.getAvailablePlaces();

            return place2 - place1;
        }
    };

    public static void SortByName(List<SessionDTO> list){
        Collections.sort(list, nameComparator);
    }

    public static Comparator<SessionDTO> nameComparator = new Comparator<SessionDTO>(){

        @Override
        public int compare(SessionDTO o1, SessionDTO o2) {
            return o1.getSessionFilm().getTitle().compareTo(o2.getSessionFilm().getTitle());
        }
    };
}
