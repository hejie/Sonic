package com.google.android.finsky.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.GservicesValue;
import com.google.android.finsky.fragments.PageFragmentHost;
import com.google.android.finsky.fragments.UrlBasedPageFragment;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.local.AssetUtils;
import com.google.android.finsky.remoting.protos.ContentFlagging.FlagContentResponse;
import com.google.android.finsky.remoting.protos.DocDetails.AppDetails;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.vending.remoting.api.VendingApi;
import com.google.android.vending.remoting.protos.VendingProtos.ModifyCommentResponseProto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlagItemFragment extends UrlBasedPageFragment
  implements FlagItemUserMessageDialog.Listener, ButtonBar.ClickListener
{
  private ViewGroup mDetailsPanel;
  private DfeDetails mDfeDetails;
  private Document mDoc;
  private boolean mDynamicButtonContainer;
  private String mFlagMessage;
  private RadioGroup mFlagRadioButtons;
  private int mSelectedRadioButtonId;
  private DetailsSummaryViewBinder mSummaryViewBinder;

  private List<FlagType> getAppFlagTypes()
  {
    String str = this.mDoc.getAppDetails().getPackageName();
    if (!FinskyApp.get().getLibraries().getAppOwners(str).isEmpty());
    for (boolean bool = true; ; bool = false)
      return AppFlagType.getAppFlags(bool);
  }

  private List<FlagType> getFlagTypesForCurrentCorpus(int paramInt)
  {
    if (paramInt == 3);
    for (List localList = getAppFlagTypes(); ; localList = MusicFlagType.getMusicFlags())
    {
      return localList;
      if (paramInt != 2)
        break;
    }
    throw new IllegalStateException("unsupported backend type");
  }

  private FlagType getSelectedFlagType()
  {
    FlagType localFlagType = null;
    if ((getView() == null) || (this.mFlagRadioButtons.getCheckedRadioButtonId() == -1));
    while (true)
    {
      return localFlagType;
      int i = this.mFlagRadioButtons.indexOfChild(getView().findViewById(this.mFlagRadioButtons.getCheckedRadioButtonId()));
      List localList = getFlagTypesForCurrentCorpus(this.mDoc.getBackend());
      if (i < localList.size())
        localFlagType = (FlagType)localList.get(i);
    }
  }

  public static FlagItemFragment newInstance(String paramString)
  {
    FlagItemFragment localFlagItemFragment = new FlagItemFragment();
    localFlagItemFragment.setDfeTocAndUrl(FinskyApp.get().getToc(), paramString);
    return localFlagItemFragment;
  }

  private void postFlag()
  {
    this.mPageFragmentHost.goBack();
    getSelectedFlagType().postFlag(this.mContext, this.mDoc, this.mFlagMessage);
  }

  protected int getLayoutRes()
  {
    return 2130968695;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    int i;
    if (paramBundle != null)
    {
      this.mFlagMessage = paramBundle.getString("flag_free_text_message");
      if (paramBundle.containsKey("flag_selected_button_id"))
      {
        i = paramBundle.getInt("flag_selected_button_id");
        this.mSelectedRadioButtonId = i;
      }
    }
    else
    {
      if ((paramBundle == null) || (!paramBundle.containsKey("doc")))
        break label72;
      onDocumentLoaded((Document)paramBundle.getParcelable("doc"));
    }
    while (true)
    {
      return;
      i = -1;
      break;
      label72: switchToLoading();
      this.mDfeDetails = new DfeDetails(this.mDfeApi, this.mUrl);
      this.mDfeDetails.addDataChangedListener(new OnDataChangedListener()
      {
        public void onDataChanged()
        {
          if (FlagItemFragment.this.mDoc == null)
            FlagItemFragment.this.onDocumentLoaded(FlagItemFragment.this.mDfeDetails.getDocument());
          while (true)
          {
            return;
            FinskyLog.d("Ignoring soft TTL refresh.", new Object[0]);
          }
        }
      });
      this.mDfeDetails.addErrorListener(new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
          String str = ErrorStrings.get(FlagItemFragment.this.getActivity(), paramAnonymousVolleyError);
          if (str != null)
            FlagItemFragment.this.mPageFragmentHost.showErrorDialog(null, str, true);
          while (true)
          {
            return;
            FlagItemFragment.this.mPageFragmentHost.goBack();
          }
        }
      });
    }
  }

  public void onDestroyView()
  {
    if (this.mSummaryViewBinder != null)
      this.mSummaryViewBinder.onDestroyView();
    super.onDestroyView();
  }

  public void onDocumentLoaded(Document paramDocument)
  {
    this.mDoc = paramDocument;
    switchToData();
    if (this.mSummaryViewBinder == null)
    {
      this.mSummaryViewBinder = BinderFactory.getSummaryViewBinder(getToc(), this.mDoc.getBackend(), this.mDfeApi.getAccount());
      this.mSummaryViewBinder.hideDynamicFeatures();
      this.mSummaryViewBinder.init(this.mContext, this.mNavigationManager, this.mBitmapLoader, this, false, null, null, null, false);
    }
    this.mFlagRadioButtons.removeAllViews();
    Iterator localIterator = getFlagTypesForCurrentCorpus(this.mDoc.getBackend()).iterator();
    while (localIterator.hasNext())
    {
      FlagType localFlagType = (FlagType)localIterator.next();
      RadioButton localRadioButton = new RadioButton(this.mContext);
      localRadioButton.setText(localFlagType.stringId);
      localRadioButton.setTextAppearance(this.mContext, 2131623936);
      localRadioButton.setTag(localFlagType);
      this.mFlagRadioButtons.addView(localRadioButton);
      if ((this.mSelectedRadioButtonId != -1) && (this.mSelectedRadioButtonId == localFlagType.stringId))
        this.mFlagRadioButtons.check(localRadioButton.getId());
    }
    getView().requestFocus();
    onDataChanged();
  }

  protected void onInitViewBinders()
  {
    View localView = getView();
    this.mDetailsPanel = ((ViewGroup)localView.findViewById(2131230881));
    this.mDynamicButtonContainer = getResources().getBoolean(2131296256);
    this.mFlagRadioButtons = ((RadioGroup)localView.findViewById(2131231042));
    final ButtonBar localButtonBar = (ButtonBar)localView.findViewById(2131230825);
    localButtonBar.setPositiveButtonTitle(2131165705);
    localButtonBar.setPositiveButtonEnabled(false);
    localButtonBar.setClickListener(this);
    this.mFlagRadioButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
      {
        localButtonBar.setPositiveButtonEnabled(true);
      }
    });
  }

  public void onNegativeButtonClick()
  {
    this.mPageFragmentHost.goBack();
  }

  public void onPositiveButtonClick()
  {
    FlagType localFlagType = getSelectedFlagType();
    if (localFlagType == null);
    while (true)
    {
      return;
      if (localFlagType.requireUserComment())
      {
        FragmentManager localFragmentManager = getFragmentManager();
        if (localFragmentManager.findFragmentByTag("flag_item_dialog") == null)
        {
          FlagItemUserMessageDialog localFlagItemUserMessageDialog = FlagItemUserMessageDialog.newInstance(localFlagType.textEntryStringId);
          localFlagItemUserMessageDialog.setTargetFragment(this, 0);
          localFlagItemUserMessageDialog.show(localFragmentManager, "flag_item_dialog");
        }
      }
      else
      {
        postFlag();
      }
    }
  }

  public void onPositiveClick(String paramString)
  {
    this.mFlagMessage = paramString;
    postFlag();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.mDoc != null)
    {
      paramBundle.putParcelable("doc", this.mDoc);
      paramBundle.putString("flag_free_text_message", this.mFlagMessage);
      if (getSelectedFlagType() != null)
        paramBundle.putInt("flag_selected_button_id", getSelectedFlagType().stringId);
    }
  }

  public void rebindActionBar()
  {
    this.mPageFragmentHost.updateBreadcrumb(this.mContext.getString(2131165686));
    this.mPageFragmentHost.updateCurrentBackendId(this.mDoc.getBackend());
  }

  public void rebindViews()
  {
    int i;
    LayoutInflater localLayoutInflater;
    if (this.mDoc != null)
    {
      TextView localTextView1 = (TextView)this.mDataView.findViewById(2131231041);
      if (this.mDoc.getBackend() != 3)
        break label225;
      i = 2131165688;
      localTextView1.setText(i);
      localLayoutInflater = LayoutInflater.from(this.mDetailsPanel.getContext());
      if (!this.mDynamicButtonContainer)
        break label232;
      View localView = this.mDataView.findViewById(2131230909);
      localView.getLayoutParams().width = CorpusResourceUtils.getLargeDetailsIconWidth(this.mContext, this.mDoc.getDocumentType());
      localView.getLayoutParams().height = getResources().getDimensionPixelSize(2131427431);
    }
    while (true)
    {
      if (this.mDoc.getBackend() == 2)
      {
        TextView localTextView2 = (TextView)this.mDataView.findViewById(2131231043);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = G.musicDmcaReportLink.get();
        localTextView2.setText(Html.fromHtml(getString(2131165691, arrayOfObject)));
        localTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        localTextView2.setVisibility(0);
      }
      DetailsSummaryViewBinder localDetailsSummaryViewBinder = this.mSummaryViewBinder;
      Document localDocument = this.mDoc;
      View[] arrayOfView = new View[1];
      arrayOfView[0] = this.mDetailsPanel;
      localDetailsSummaryViewBinder.bind(localDocument, false, arrayOfView);
      rebindActionBar();
      return;
      label225: i = 2131165690;
      break;
      label232: this.mDetailsPanel.removeAllViews();
      localLayoutInflater.inflate(CorpusResourceUtils.getCorpusDetailsLayoutResource(this.mDoc.getBackend()), this.mDetailsPanel, true);
    }
  }

  protected void requestData()
  {
  }

  public static class AppFlagType extends FlagItemFragment.FlagType
  {
    private final int mRpcId;

    private AppFlagType(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt3);
      this.mRpcId = paramInt1;
    }

    public static List<FlagItemFragment.FlagType> getAppFlags(boolean paramBoolean)
    {
      boolean bool = ((Boolean)G.vendingHideContentRating.get()).booleanValue();
      ArrayList localArrayList = Lists.newArrayList();
      localArrayList.add(new AppFlagType(1, 2131165692, -1));
      localArrayList.add(new AppFlagType(2, 2131165693, -1));
      localArrayList.add(new AppFlagType(3, 2131165694, -1));
      if (paramBoolean)
        localArrayList.add(new AppFlagType(4, 2131165695, 2131165702));
      if (!bool)
        localArrayList.add(new AppFlagType(6, 2131165696, -1));
      localArrayList.add(new AppFlagType(5, 2131165697, 2131165701));
      return localArrayList;
    }

    public void postFlag(final Context paramContext, Document paramDocument, String paramString)
    {
      FinskyApp.get().getVendingApi().flagAsset(AssetUtils.makeAssetId(paramDocument.getAppDetails()), this.mRpcId, paramString, new Response.Listener()
      {
        public void onResponse(VendingProtos.ModifyCommentResponseProto paramAnonymousModifyCommentResponseProto)
        {
          Toast.makeText(paramContext, 2131165703, 1).show();
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
        }
      });
    }
  }

  public static abstract class FlagType
  {
    public final int stringId;
    public final int textEntryStringId;

    protected FlagType(int paramInt1, int paramInt2)
    {
      this.stringId = paramInt1;
      this.textEntryStringId = paramInt2;
    }

    public abstract void postFlag(Context paramContext, Document paramDocument, String paramString);

    public boolean requireUserComment()
    {
      if (this.textEntryStringId != -1);
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }

  public static class MusicFlagType extends FlagItemFragment.FlagType
  {
    private final int mContentFlagType;

    private MusicFlagType(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt3);
      this.mContentFlagType = paramInt1;
    }

    public static List<FlagItemFragment.FlagType> getMusicFlags()
    {
      ArrayList localArrayList = Lists.newArrayList();
      localArrayList.add(new MusicFlagType(5, 2131165700, 2131165701));
      localArrayList.add(new MusicFlagType(1, 2131165692, 2131165701));
      localArrayList.add(new MusicFlagType(4, 2131165694, 2131165701));
      localArrayList.add(new MusicFlagType(6, 2131165699, 2131165701));
      localArrayList.add(new MusicFlagType(2, 2131165698, 2131165701));
      localArrayList.add(new MusicFlagType(8, 2131165697, 2131165701));
      return localArrayList;
    }

    public void postFlag(final Context paramContext, Document paramDocument, String paramString)
    {
      FinskyApp.get().getDfeApi().flagContent(paramDocument.getDocId(), this.mContentFlagType, paramString, new Response.Listener()
      {
        public void onResponse(ContentFlagging.FlagContentResponse paramAnonymousFlagContentResponse)
        {
          Toast.makeText(paramContext, 2131165703, 1).show();
        }
      }
      , new Response.ErrorListener()
      {
        public void onErrorResponse(VolleyError paramAnonymousVolleyError)
        {
        }
      });
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.10\classes-dex2jar.jar
 * Qualified Name:     com.google.android.finsky.activities.FlagItemFragment
 * JD-Core Version:    0.6.2
 */