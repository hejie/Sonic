package com.google.android.finsky.exploreactivity;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;

public class FadeAdapter
{
  private final ColorRGBA mColor = new ColorRGBA();
  private final long mFadeDelay;
  private final float mFadeInDuration;
  private final float mFadeOutDuration;
  private float mFadeProgress = 0.0F;
  private long mFadeStartTime;
  private boolean mFadingIn = false;
  private final Geometry[] mGeometries;

  public FadeAdapter(float paramFloat1, float paramFloat2, float paramFloat3, Geometry[] paramArrayOfGeometry)
  {
    this.mGeometries = paramArrayOfGeometry;
    this.mFadeInDuration = paramFloat1;
    this.mFadeOutDuration = paramFloat2;
    this.mFadeDelay = (()(1000.0F * paramFloat3));
  }

  public void fade(boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.mFadingIn) && (this.mFadeDelay > 0L))
      this.mFadeStartTime = System.currentTimeMillis();
    this.mFadingIn = paramBoolean;
  }

  public float getFadeProgress()
  {
    return this.mFadeProgress;
  }

  public boolean isFadingIn()
  {
    return this.mFadingIn;
  }

  public boolean isVisible()
  {
    if (this.mFadeProgress > 0.0F);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public void makeInvisible()
  {
    this.mFadeProgress = 0.0F;
    this.mFadingIn = false;
  }

  public void reset()
  {
    if (this.mFadingIn);
    for (float f = 0.0F; ; f = 1.0F)
    {
      this.mFadeProgress = f;
      return;
    }
  }

  public void update(float paramFloat)
  {
    if ((this.mFadingIn) && (this.mFadeDelay > 0L) && (System.currentTimeMillis() - this.mFadeStartTime < this.mFadeDelay))
      return;
    label59: int i;
    label81: Material localMaterial;
    if (this.mFadingIn)
    {
      this.mFadeProgress = Math.min(1.0F, this.mFadeProgress + paramFloat / this.mFadeInDuration);
      this.mColor.set(1.0F, 1.0F, 1.0F, this.mFadeProgress * this.mFadeProgress);
      i = 0;
      if (i < this.mGeometries.length)
      {
        localMaterial = this.mGeometries[i].getMaterial();
        if (localMaterial != null)
          break label134;
      }
    }
    while (true)
    {
      i++;
      break label81;
      break;
      this.mFadeProgress = Math.max(0.0F, this.mFadeProgress - paramFloat / this.mFadeOutDuration);
      break label59;
      label134: localMaterial.setColor("Color", this.mColor);
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.exploreactivity.FadeAdapter
 * JD-Core Version:    0.6.2
 */