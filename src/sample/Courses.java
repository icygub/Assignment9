package sample;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by icyhot on 7/3/2017.
 */
public class Courses {

    private ArrayList<CourseOffering> courseList;

    public Courses() {
        courseList = new ArrayList<>();
    }

    public CourseOffering searchByName(String name) {

        int lower = 0;
        int upper = courseList.size() - 1;
        int middle;

        while(true) {
            middle = (upper - lower) / 2 + lower;

            if(courseList.get(middle).getCourseName().compareTo(name) == 0) {
                return courseList.get(middle);
            } else if (name.compareTo(courseList.get(middle).getCourseName()) > 0) {
                lower = middle;
            } else if (name.compareTo(courseList.get(middle).getCourseName()) < 0){
                upper = middle;
            }
        }
    }

    public CourseOffering searchByNumber(int number) {
        int lower = 0;
        int upper = courseList.size() - 1;
        int middle;

        while(true) {
            middle = (upper - lower) / 2 + lower;

            if(courseList.get(middle).getCourseNumber() == number) {
                return courseList.get(middle);
            } else if (number > middle && number <= upper) {
                lower = middle;
            } else if (number < middle && number >= lower){
                upper = middle;
            }
        }
    }

    public void add(CourseOffering courseOffering) {
        courseList.add(courseOffering);
    }

    public ArrayList<CourseOffering> getCourseList() {
        return courseList;
    }

    public void quickSort(ArrayList<CourseOffering> arr, int left, int right) {
        int index = divideAndConquer(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }

    public int divideAndConquer(ArrayList<CourseOffering> arr, int left, int right)
    {
        int i = left, j = right;
        int pivot = arr.get((left + right) / 2).getCourseNumber();

        while (i <= j) {
            while (arr.get(i).getCourseNumber() < pivot)
                i++;
            while (arr.get(j).getCourseNumber() > pivot)
                j--;
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        return i;
    }

    public void swap(ArrayList<CourseOffering> arrayList, int i, int j) {
        Collections.swap(arrayList, i, j);
//        CourseOffering temp = arrayList.get(i);
//        arrayList.set(i, arrayList.get(j));
//        arrayList.set(j, temp);
    }

}



//    public void quickSort(ArrayList<Integer> arrayList, int lower, int upper) {
//        int i = lower;
//        int j = upper;
//        int pivotNum = arrayList.get((lower + upper) / 2);
//
//        while(i <= j) {
//            while(arrayList.get(i) < pivotNum)
//                i++;
//            while(arrayList.get(j) > pivotNum)
//                j--;
//
//            swap(arrayList, i, j);
//
//            if(i >= j)
//                return i;
//        }
//    }


//    public void swap(ArrayList<Integer> arrayList, int i, int j) {
//        int temp = arrayList.get(i);
//        arrayList.set(i, arrayList.get(j));
//        arrayList.set(j, temp);
//    }
