package LAB1.ex1;

import java.util.*;
import java.util.function.IntBinaryOperator;

public class Set {
  private ArrayList<Integer> a;

  public Set() {
    a = new ArrayList<Integer>();
  }

  public int[] toArray() {
    int[] ia = new int[a.size()];
    for (int i = 0; i < ia.length; i++) {
        ia[i] = a.get(i);
    }
    return ia;
  }

  public void insert(int x) {
    boolean shouldAdd = true;

    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) > x) {
          a.add(i, x);
          shouldAdd = false;
          break;
      } else {
        if (a.get(i) == x) {
            shouldAdd = false;
            break;
        }
      }
    }
    if (shouldAdd) a.add(x);
  }

  public boolean member(int x) {
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) > x) {
        return false;
      } else {
        if (a.get(i) == x) {
          return true;
        }
      }
    }
    return false;
  }

  public void intersect(Set s) {
    int i = 0;

    for(int j = 0; i < a.size() && j < s.a.size();) {
      if (a.get(i).equals(s.a.get(j))){
        i++;
        j++;
      } else {
        if (a.get(i) < s.a.get(j)) {
          a.remove(i);
        } else {
          j++;
        }
      }
    }
    // If all elements in Set s have been exhausted, then the remaining elements in Set a should be removed
    while (i < a.size()) {
        a.remove(i);
    }
  }

  // Try with:
  //   (a, b) -> a + b;
  //   (a, b) -> a - b;
  public boolean distinctClosed(IntBinaryOperator f) {
    int vi,vj;
    for (int i = 0; i < a.size(); i++) {
      for (int j = i; j < a.size(); j++) {
        vi = a.get(i);
        vj = a.get(j);
        if (!(member(f.applyAsInt(vi, vj)) || vi == vj)) return false;
      }
    }
    return true;
  }
}
