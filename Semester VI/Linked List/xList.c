#include "xList.h"

#include <stdlib.h>

/**
 * @author Jacob Doiron
 * @assignment Create a singly LinkedList
*/

/**
 * @param data The data to store in the element
 * @param next A pointer to the next element in the singly LinkedList
 * @typedef xList_Element_ A struct which contains its associated data and the next element in the singly
 * LinkedList
*/
typedef struct xList_Element_ {
  void* data;
  xList_Element_Ptr next;
} xList_Element;

/**
 * @param head The head element of the singly LinkedList
 * @param tail The tail element of the singly LinkedList
 * @typedef xList_ A struct which contains the head and tail elements in a singly LinkedList
*/
typedef struct xList_ {
  xList_Element_Ptr head;
  xList_Element_Ptr tail;
} xList;

/**
 * @return xList_Ptr Returns a pointer to an empty singly LinkedList
 * A function which creates an empty singly LinkedList with NULL head and tail elements, and returns it
*/
xList_Ptr xList_create()
{
  xList* list = (xList*) malloc(sizeof(xList));
  list -> head = NULL;
  list -> tail = NULL;
  return list;
}

/**
 * @param list A singly LinkedList to destroy
 * A function which takes a pointer to a singly LinkedList and destroys its elements and the list itself
*/
void xList_destroy(xList_Ptr list)
{
  xList_Element_Ptr element = list -> head;
  while (element != NULL) {
    free(element);
    element = element -> next;
  }
  list = NULL;
}

/**
 * @param list A singly LinkedList to insert an element into
 * @param element The element in front of the element to insert
 * @param data Data to insert into the new element
 * A function which inserts a new element into a singly LinkedList, after the specified element. If the
 * element is NULL, the new element is inserted at the head of the list. It creates a new element with the
 * specified data and sets the list's head, if it was previously the empty list. It also updates the tail,
 * when necessary
*/
void xList_insert(xList_Ptr list, xList_Element_Ptr element, void* data)
{
  if (element == NULL) {
    element = (xList_Element_Ptr) malloc(sizeof(xList_Element_Ptr));
    element -> data = data;
    if (list -> head == NULL) {
      element -> next = NULL;
      list -> tail = element;
    } else {
      element -> next = list -> head;
    }
    list -> head = element;
  } else {
    xList_Element_Ptr temp = (xList_Element_Ptr) malloc(sizeof(xList_Element_Ptr));
    temp -> data = data;
    temp -> next = element -> next;
    if (temp -> next == NULL) {
      list -> tail = temp;
    }
    element -> next = temp;
  }
}

/**
 * @param list A singly LinkedList to remove an element from
 * @param element The element in front of the element to delete
 * A function which deletes an element from a singly LinkedList, after the specified element. If the
 * element is NULL, the function removes the element at the head of the list, setting the head to the new
 * element and the tail as well, if the list becomes the empty list.
*/
void xList_remove(xList_Ptr list, xList_Element_Ptr element)
{
  if (element == NULL) {
    xList_Element_Ptr temp = list -> head;
    if (temp != NULL) {
      list -> head = temp -> next;
      if (list -> head == NULL) {
        list -> tail = NULL;
      }
      free(temp);
    }
  } else {
    if (element -> next != NULL) {
      xList_Element_Ptr temp = element -> next;
      element -> next = temp -> next;
      free(temp);
      if (element -> next == NULL) {
        list -> tail = element;
      }
    }
  }
}

/**
 * @param list A singly LinkedList whose size is to be determined
 * @return uint32_t Returns a 32-bit integer of the size of the specified singly LinkedList
 * A function which takes a singly LinkedList and determines its size by iterating through the elements.
 * Once it finds the tail element (when the currently iterating element's next element is NULL), it exits
 * the loop
*/
uint32_t xList_size(xList_Ptr list)
{
  int size = 0;
  xList_Element_Ptr temp = list -> head;
  while (temp != NULL) {
    size++;
    temp = temp -> next;
  }
  return size;
}

/**
 * @param list A singly LinkedList whose head will be returned
 * @return xList_Element_Ptr Returns the head element of the specified singly LinkedList
 * A function which takes a singly LinkedList and returns the head element
*/
xList_Element_Ptr xList_first(xList_Ptr list)
{
  return list -> head;
}

/**
 * @param list A singly LinkedList whose tail will be returned
 * @return xList_Element_Ptr Returns the tail element of the specified singly LinkedList
 * A function which takes a singly LinkedList and returns the tail element
*/
xList_Element_Ptr xList_last(xList_Ptr list)
{
  return list -> tail;
}

/**
 * @param element An element whose data will be returned
 * @return void* Returns the data associated with the specified element
 * A function which takes an element and returns its associated data
*/
void* xList_data(xList_Element_Ptr element)
{
  return element -> data;
}

/**
 * @param element An element whose next element will be returned
 * @return xList_Element_Ptr Returns the next element chaining from the specified element
 * A function which takes an element and returns the next element in the singly LinkedList
*/
xList_Element_Ptr xList_next(xList_Element_Ptr element)
{
  return element -> next;
}
