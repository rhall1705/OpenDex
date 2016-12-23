package personal.rowan.sandbox.ui.detail;

import android.text.SpannableStringBuilder;

/**
 * Created by Rowan Hall
 */

public class DetailViewModel {

    private String mName;
    private String mArtworkUrl;
    private String mModelUrl;
    private DetailFlavorCardViewModel mFlavorCardViewModel;
    private DetailSpritesCardViewModel mSpritesCardViewModel;
    private DetailStatsCardViewModel mStatsCardViewModel;

    DetailViewModel(String name, String artworkUrl, String modelUrl) {
        mName = name;
        mArtworkUrl = artworkUrl;
        mModelUrl = modelUrl;
    }

    void setValues(String type, String weight, String height, String abilities,
                    String frontSpriteUrl, String backSpriteUrl,
                    String HP, String ATK, String DEF, String SPATK, String SPDEF, String SPD) {
        mFlavorCardViewModel = new DetailFlavorCardViewModel(type, weight, height, abilities);
        mSpritesCardViewModel = new DetailSpritesCardViewModel(frontSpriteUrl, backSpriteUrl);
        mStatsCardViewModel = new DetailStatsCardViewModel(HP, ATK, DEF, SPATK, SPDEF, SPD);
    }

    void setPokedexEntries(SpannableStringBuilder pokedexEntries) {
        mFlavorCardViewModel.setPokedexEntries(pokedexEntries);
    }

    boolean hasValues() {
        return mFlavorCardViewModel != null &&
                mSpritesCardViewModel != null &&
                mStatsCardViewModel != null;
    }

    boolean hasPokedexEntries() {
        return mFlavorCardViewModel != null &&
                mFlavorCardViewModel.hasPokedexEntries();
    }

    public String getName() {
        return mName;
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public String getModelUrl() {
        return mModelUrl;
    }

    public DetailFlavorCardViewModel getFlavorCardViewModel() {
        return mFlavorCardViewModel;
    }

    public DetailSpritesCardViewModel getSpritesCardViewModel() {
        return mSpritesCardViewModel;
    }

    public DetailStatsCardViewModel getStatsCardViewModel() {
        return mStatsCardViewModel;
    }

    public class DetailFlavorCardViewModel {

        private String mType;
        private String mWeight;
        private String mHeight;
        private String mAbilities;
        private SpannableStringBuilder mPokedexEntries;

        DetailFlavorCardViewModel(String formattedType, String weight, String height, String abilities) {
            mType = formattedType;
            mWeight = weight;
            mHeight = height;
            mAbilities = abilities;
        }

        void setPokedexEntries(SpannableStringBuilder pokedexEntries) {
            mPokedexEntries = pokedexEntries;
        }

        boolean hasPokedexEntries() {
            return mPokedexEntries != null;
        }

        public String getType() {
            return mType;
        }

        public String getWeight() {
            return mWeight;
        }

        public String getHeight() {
            return mHeight;
        }

        public String getAbilities() {
            return mAbilities;
        }

        public SpannableStringBuilder getPokedexEntries() {
            return mPokedexEntries;
        }
    }

    public class DetailSpritesCardViewModel {

        private String mFrontSpriteUrl;
        private String mBackSpriteUrl;

        DetailSpritesCardViewModel(String frontSpriteUrl, String backSpriteUrl) {
            mFrontSpriteUrl = frontSpriteUrl;
            mBackSpriteUrl = backSpriteUrl;
        }

        public String getFrontSpriteUrl() {
            return mFrontSpriteUrl;
        }

        public String getBackSpriteUrl() {
            return mBackSpriteUrl;
        }

    }

    public class DetailStatsCardViewModel {

        private String mHP;
        private String mATK;
        private String mDEF;
        private String mSPATK;
        private String mSPDEF;
        private String mSPD;

        DetailStatsCardViewModel (String HP, String ATK, String DEF, String SPATK, String SPDEF, String SPD) {
            mHP = HP;
            mATK = ATK;
            mDEF = DEF;
            mSPATK = SPATK;
            mSPDEF = SPDEF;
            mSPD = SPD;
        }

        public String getHP() {
            return mHP;
        }

        public String getATK() {
            return mATK;
        }

        public String getDEF() {
            return mDEF;
        }

        public String getSPATK() {
            return mSPATK;
        }

        public String getSPDEF() {
            return mSPDEF;
        }

        public String getSPD() {
            return mSPD;
        }
    }

}
