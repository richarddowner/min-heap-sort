/*
 *  Richard Downer
 *  
 *  Using Min Binary Heap
 *  Insert to k elements
 *  Check if each element after k
 *  is larger than min element in
 *  the heap.
 *  If so, insert then delete min.
 *  else do nothing.
 *  
 */
import java.util.*;
import java.io.*;
public class MinHeap {

  public static void main(String[] args) {
	  
	  try {
		  
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  int k = Integer.parseInt(br.readLine().trim());
		  String separator = br.readLine().trim();
		  
		  if (k != 0) { // If k is 0 there is nothing to sort.
			  
			  int countRecords = 0;
			  BinaryHeapMin heap = new BinaryHeapMin();
			  
			  MP3SongData records[] = new MP3SongData[k];
			  
			  try {
				  
				  String[] totalData = null;
				  String data = null;
				  
				  while((data = br.readLine()) != null) {
					  totalData = data.trim().split(separator);
					  
					  MP3SongData s = new MP3SongData(totalData[0], totalData[1], Integer.parseInt(totalData[2].trim()));
					  
					  // The Magic:
					  if(countRecords < k) {
				    	  // Add in all first k elements
				    	  heap.add(s);
				    	  countRecords++;
				      } else {
				    	  // After k elements are inserted
				    	  // Only if s is larger than min add s into heap
				    	  // then delete smallest from heap
				    	  if(s.compare(heap.min()) == 1) {
				    	  	 heap.add(s);
				    	  	 heap.remove();
				    	  } 
				      } 
				  }
				  
				  int i=0;
			      while(!heap.isEmpty()) {
			    	  
			    	  records[i] = heap.remove();
			    	  i++;
			      }
			      for(int j=k-1; j>=0; j--) {
			    	  if(records[j] != null) {
			    		  System.out.println(records[j]);
			    	  }
			      } 
				  
			  } finally {
				  br.close();
			  }
		  }
		  
	  } catch(Exception e) {
		  System.out.println(e);
	  }	  
  }
  
  /*
  public static void insertion_srt(List<MP3SongData>a, int left, int right){
		for (int i = left; i < right; i++){
			int j = i;
			MP3SongData B = a.get(i);
			while ((j > 0) && (a.get(j-1).compare(B)) == 1){
				//array[j] = array[j-1];
				a.set(j, a.get(j-1));
				j--;
			}
			//array[j] = B;
			a.set(j, B);
		}
	}
	*/
}


class BinaryHeapMin {
  List<MP3SongData> h = new ArrayList<MP3SongData>();

  public BinaryHeapMin() {}

  public void add(MP3SongData node) {
    h.add(null);
    int k = h.size() - 1;
    while (k > 0) {
      int parent = (k - 1) / 2;
      MP3SongData p = h.get(parent);
      if(node.compare(p) >= 0) {  
        break;
      }
      h.set(k, p);
      k = parent;
    }
    h.set(k, node);
  }

  public MP3SongData remove() {
	MP3SongData removedNode = h.get(0);
	MP3SongData lastNode = h.remove(h.size() - 1); // remove might be an issue with primitive array?
    percolateDown(0, lastNode);
    return removedNode;
  }

  public MP3SongData min() {
    return h.get(0);
  }

  public boolean isEmpty() {
    return h.isEmpty();
  }

  void percolateDown(int k, MP3SongData node) {
    if (h.isEmpty()) {
      return;
    }
    while (k < h.size() / 2) {
      int child = 2 * k + 1;
      if (child < h.size() - 1 && h.get(child).compare(h.get(child + 1)) > 0) {
        child++;
      }
      if (node.compare(h.get(child)) <= 0) {
        break;
      }
      h.set(k, h.get(child));
      k = child;
    }
    h.set(k, node);
  } 
}

class MP3SongData {

    private String songTitle;
    private String songAuthor;
    private int songRunTime;

    public MP3SongData() {}
    
    public MP3SongData(String title, String author, int runTime) {
    	this.songTitle = title;
    	this.songAuthor = author;
    	this.songRunTime = runTime;
    }

    public String getSongTitle() {
            return this.songTitle;
    }

    public String getSongAuthor() {
            return this.songAuthor;
    }

    public int getSongRunTime() {
            return this.songRunTime;
    }

    public void setSongTitle(String title) {
            this.songTitle = title;
    }

    public void setSongAuthor(String author) {
            this.songAuthor = author;
    }

    public void setSongRunTime(int runTime) {
            this.songRunTime = runTime;
    }
    
    public int compare(MP3SongData o2) {
        if(this.songRunTime > o2.getSongRunTime()) {
                return 1;
        }
        if(this.songRunTime < o2.getSongRunTime()) {
                return -1;
        }
        if(this.songRunTime == o2.getSongRunTime()) {

                if(this.songTitle.compareTo(o2.getSongTitle()) > 0) {
                        return -1;
                }
                if(this.songTitle.compareTo(o2.getSongTitle()) < 0) {
                        return 1;
                }
                if(this.songTitle.compareTo(o2.getSongTitle()) == 0) {
                        if(this.songAuthor.compareTo(o2.getSongAuthor()) < 0) {
                                return 1;
                        }
                        if(this.songAuthor.compareTo(o2.getSongAuthor()) > 0) {
                                return -1;
                        }
                }
        }
        return 1;
    } 

    public String toString(){
            return songTitle + "&" + songAuthor + "&" + songRunTime;
    }

}
