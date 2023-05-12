package com.ssp.closet.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Sequence implements Serializable {

   /* Private Fields */
   private String name;
   private int nextId;
   private String color;
   private String size;

   /* Constructors */

   public Sequence() { }

   public Sequence(String name, int nextId) {
      this.name = name;
      this.nextId = nextId;
   }
  /* JavaBeans Properties */

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public int getNextId() { return nextId; }
   public void setNextId(int nextId) { this.nextId = nextId; }

   public String getColor() {return color;}
   public void setColor(String color) { this.color = color;}

   public String getSize() {return size;}
   public void setSize(String size) { this.size = size;}

}
