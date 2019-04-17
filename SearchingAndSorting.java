package sample;

import java.util.ArrayList;

public class SearchingAndSorting {

    //creating a method to sort the array
    public ArrayList<Integer> selectionSort(ArrayList<Integer> sortingArray){   //passing the array that we want to sort as a parameter
        //iterate through the array
        for (int i=0; i<sortingArray.size()-1; i++){
            int index = i;
            for (int j=i+1; j<sortingArray.size(); j++){
                //if the current element is greater than the next element swap those
                if (sortingArray.get(j) < sortingArray.get(index)) {
                    index = j;
                }
            }
            //swap the elements
            int temp = sortingArray.get(index);
            sortingArray.set(index, sortingArray.get(i));
            sortingArray.set(i, temp);
        }
        //return the sorted array
        return sortingArray;
    }


    //creste a method for searching
    public int binarySearch(ArrayList<Integer> searchingArray, int key){  //passing the array and the key we want to search as parameters
        //declaring and initializing variables
        int lowest = 0;
        int highest = searchingArray.size()-1;
        int mid = 0;
        while (lowest <= highest){
            mid = (lowest + highest)/2;
            if (key == searchingArray.get(mid)){
                return mid;
            }
            if (key < searchingArray.get(mid)){
                highest = mid-1;
            }
            else {
                lowest = mid+1;
            }
        }
        return -1;
    }
}
