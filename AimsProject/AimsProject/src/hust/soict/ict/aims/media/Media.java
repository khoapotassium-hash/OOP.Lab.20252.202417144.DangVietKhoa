package hust.soict.ict.aims.media;

import java.util.Comparator;

public abstract class Media {
    private static int nextId = 0;

    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    private static int generateId() {
        return ++nextId;
    }

    private static void updateNextId(int id) {
        if (id > nextId) {
            nextId = id;
        }
    }

    public Media(String title, String category, float cost) {
        this(generateId(), title, category, cost);
    }

    public Media(int id, String title, String category, float cost) {
        updateNextId(id);
        this.id = id;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }
    public void setId(int id) { updateNextId(id); this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCategory(String category) { this.category = category; }
    public void setCost(float cost) { this.cost = cost; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Media)) return false;
        Media media = (Media) obj;
        return this.id == media.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "Media: " + title 
        + " (Category: " + category 
        + ", Cost: " + cost + "$)";
    }
}

