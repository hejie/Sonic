package com.google.android.finsky.adapters;

public enum UnevenGridItemType
{
  static
  {
    GRAPHIC_2x1 = new UnevenGridItemType("GRAPHIC_2x1", 1);
    DOC_DETAILS_WITH_REFLECTED_PROMO_2x2 = new UnevenGridItemType("DOC_DETAILS_WITH_REFLECTED_PROMO_2x2", 2);
    GRAPHIC_4x2 = new UnevenGridItemType("GRAPHIC_4x2", 3);
    GRAPHIC_COLORED_TITLE_4x2 = new UnevenGridItemType("GRAPHIC_COLORED_TITLE_4x2", 4);
    GRAPHIC_COLORED_TITLE_2x1 = new UnevenGridItemType("GRAPHIC_COLORED_TITLE_2x1", 5);
    GRAPHIC_2x1_TITLE_TOP_LEFT = new UnevenGridItemType("GRAPHIC_2x1_TITLE_TOP_LEFT", 6);
    DOCUMENT_2x1 = new UnevenGridItemType("DOCUMENT_2x1", 7);
    CORPUS_2xN = new UnevenGridItemType("CORPUS_2xN", 8);
    UnevenGridItemType[] arrayOfUnevenGridItemType = new UnevenGridItemType[9];
    arrayOfUnevenGridItemType[0] = LIST_FOUR_BLOCK_4x2;
    arrayOfUnevenGridItemType[1] = GRAPHIC_2x1;
    arrayOfUnevenGridItemType[2] = DOC_DETAILS_WITH_REFLECTED_PROMO_2x2;
    arrayOfUnevenGridItemType[3] = GRAPHIC_4x2;
    arrayOfUnevenGridItemType[4] = GRAPHIC_COLORED_TITLE_4x2;
    arrayOfUnevenGridItemType[5] = GRAPHIC_COLORED_TITLE_2x1;
    arrayOfUnevenGridItemType[6] = GRAPHIC_2x1_TITLE_TOP_LEFT;
    arrayOfUnevenGridItemType[7] = DOCUMENT_2x1;
    arrayOfUnevenGridItemType[8] = CORPUS_2xN;
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.adapters.UnevenGridItemType
 * JD-Core Version:    0.6.2
 */