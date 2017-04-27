public class Entry
implements Comparable<Entry>{
    // Name
    private String code;

    public String getCode(){ return this.code; }

    // Quantity
    private int quantity;

    public int getQuantity(){ return this.quantity; }

    public void setQuantity(int quantity){ this.quantity = quantity; }

    // Note
    private String note;

    public String getNote(){ return this.note; }

    public void setNote(String note){ this.note = note; }

    // Constructor
    public Entry(String code, int quantity, String note){
        this.code = code;
        this.quantity = quantity;
        this.note = note;
    }

    public Entry(String code){
        this.code = code;
    }

    @Override
    public String toString(){
        return String.format("-- %s%n-- %d%n-- %s%n",
            this.getCode(), this.getQuantity(), this.getNote());
    }

    @Override
    public boolean equals(Object object){
        if (object == null || !(object instanceof Entry))
            return false;

        Entry entry = (Entry)object;

        return this.getCode().equalsIgnoreCase(entry.getCode());
    }

    @Override
    public int compareTo(Entry e){
        return this.getCode().compareTo(e.getCode());
    }
}
