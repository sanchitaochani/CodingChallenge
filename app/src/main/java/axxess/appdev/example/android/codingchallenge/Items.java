package axxess.appdev.example.android.codingchallenge;

class Items {

    private String imageID;
    private String imageTitle;

    public Items(String id, String title) {
        imageID = id;
        imageTitle = title;
    }

    public void setImageID(String id) {
        imageID = id;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageTitle(String title) {
        imageTitle = title;
    }

    public String getImageTitle() {
        return imageTitle;
    }

}
