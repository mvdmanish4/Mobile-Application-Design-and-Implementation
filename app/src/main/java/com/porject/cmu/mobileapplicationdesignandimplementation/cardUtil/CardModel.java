package com.porject.cmu.mobileapplicationdesignandimplementation.cardUtil;

/**
 * Created by Manish Varma D on 14-08-2018.
 */

public class CardModel {

    private int imageId;
    private int titleId;
    private int subtitleId;

    public CardModel(int imageId, int titleId, int subtitleId) {
        this.imageId = imageId;
        this.titleId = titleId;
        this.subtitleId = subtitleId;
    }

    public int getImageId() {
        return imageId;
    }

    public int getTitle() {
        return titleId;
    }

    public int getSubtitle() {
        return subtitleId;
    }

}
