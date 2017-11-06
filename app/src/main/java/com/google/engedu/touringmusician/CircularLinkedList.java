/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.touringmusician;


import android.graphics.Point;

import java.util.Iterator;

public class CircularLinkedList implements Iterable<Point> {

    private class Node {
        Point point;
        Node prev, next;
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        public Node(Point point){
            this.point = point;
        }

    }

    Node head;


    public boolean isEmpty(){
        return head==null;
    }
    public void insertBeginning(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        Node temp = new Node(p);
        if(isEmpty()){
            head=temp;
            head.next=head;
            head.prev=head;

        }
        else {

            temp.prev=head.prev;
            head.prev.next=temp;
            head.prev=temp;
            temp.next=head;
            head=temp;


        }


    }

    private float distanceBetween(Point from, Point to) {
        return (float) Math.sqrt(Math.pow(from.y-to.y, 2) + Math.pow(from.x-to.x, 2));
    }

    public float totalDistance() {
        float total = 0;
        CircularLinkedListIterator iter = new CircularLinkedListIterator();

        while(iter.hasNext()){
            Point p1= iter.current.point;
            Point p2 = iter.current.next.point;
            total += distanceBetween(p1,p2);
            iter.next();
        }
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        return total;
    }

    public void insertNearest(Point p) {
            System.out.println("Closest");
            CircularLinkedListIterator iter = new CircularLinkedListIterator();
            Node newNode = new Node(p);

            if(head==null){
                head=newNode;
                head.next=newNode;
                head.prev=newNode;

            }else {


            Node min = null;
            float minDistance = 99999;
            while (iter.hasNext()) {
                Point temp = iter.current.point;
                if (minDistance > distanceBetween(p, temp)) {
                    minDistance = distanceBetween(p, temp);
                    min = iter.current;
                }
                iter.next();

            }
            newNode.next=min.next;
            min.next=newNode;
            newNode.prev=min;

            //System.out.println(min.point);

        }


        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
    }

    public void insertSmallest(Point p) {
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        //TODO
    }

    public void reset() {
        head = null;

    }

    private class CircularLinkedListIterator implements Iterator<Point> {

        Node current;

        public CircularLinkedListIterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Point next() {
            Point toReturn = current.point;
            current = current.next;
            if (current == head) {
                current = null;
            }
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new CircularLinkedListIterator();
    }


}
