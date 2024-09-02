package com.example.testeaiko.databinding;
import com.example.testeaiko.R;
import com.example.testeaiko.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class LineSearchResultCardBindingImpl extends LineSearchResultCardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    @NonNull
    private final android.widget.ImageView mboundView4;
    @NonNull
    private final android.widget.ImageView mboundView5;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public LineSearchResultCardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private LineSearchResultCardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView4 = (android.widget.ImageView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.ImageView) bindings[5];
        this.mboundView5.setTag(null);
        this.tvLine.setTag(null);
        this.tvSign.setTag(null);
        this.tvTerminals.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.terminal2 == variableId) {
            setTerminal2((java.lang.String) variable);
        }
        else if (BR.terminal1 == variableId) {
            setTerminal1((java.lang.String) variable);
        }
        else if (BR.isCircular == variableId) {
            setIsCircular((java.lang.Boolean) variable);
        }
        else if (BR.direction == variableId) {
            setDirection((java.lang.Boolean) variable);
        }
        else if (BR.line == variableId) {
            setLine((java.lang.String) variable);
        }
        else if (BR.sign == variableId) {
            setSign((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setTerminal2(@Nullable java.lang.String Terminal2) {
        this.mTerminal2 = Terminal2;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.terminal2);
        super.requestRebind();
    }
    public void setTerminal1(@Nullable java.lang.String Terminal1) {
        this.mTerminal1 = Terminal1;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.terminal1);
        super.requestRebind();
    }
    public void setIsCircular(@Nullable java.lang.Boolean IsCircular) {
        this.mIsCircular = IsCircular;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.isCircular);
        super.requestRebind();
    }
    public void setDirection(@Nullable java.lang.Boolean Direction) {
        this.mDirection = Direction;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.direction);
        super.requestRebind();
    }
    public void setLine(@Nullable java.lang.String Line) {
        this.mLine = Line;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.line);
        super.requestRebind();
    }
    public void setSign(@Nullable java.lang.String Sign) {
        this.mSign = Sign;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.sign);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String terminal1JavaLangStringTerminal2 = null;
        java.lang.String directionTerminal1JavaLangStringTerminal2Terminal2JavaLangStringTerminal1 = null;
        java.lang.String terminal2 = mTerminal2;
        java.lang.String terminal1JavaLangString = null;
        java.lang.String terminal1 = mTerminal1;
        boolean androidxDatabindingViewDataBindingSafeUnboxDirection = false;
        java.lang.Boolean isCircular = mIsCircular;
        java.lang.Boolean direction = mDirection;
        java.lang.String terminal2JavaLangStringTerminal1 = null;
        int isCircularViewVISIBLEViewGONE = 0;
        boolean androidxDatabindingViewDataBindingSafeUnboxIsCircular = false;
        java.lang.String line = mLine;
        java.lang.String terminal2JavaLangString = null;
        java.lang.String sign = mSign;
        int isCircularViewGONEViewVISIBLE = 0;

        if ((dirtyFlags & 0x44L) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(isCircular)
                androidxDatabindingViewDataBindingSafeUnboxIsCircular = androidx.databinding.ViewDataBinding.safeUnbox(isCircular);
            if((dirtyFlags & 0x44L) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxIsCircular) {
                        dirtyFlags |= 0x400L;
                        dirtyFlags |= 0x1000L;
                }
                else {
                        dirtyFlags |= 0x200L;
                        dirtyFlags |= 0x800L;
                }
            }


                // read androidx.databinding.ViewDataBinding.safeUnbox(isCircular) ? View.VISIBLE : View.GONE
                isCircularViewVISIBLEViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxIsCircular) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                // read androidx.databinding.ViewDataBinding.safeUnbox(isCircular) ? View.GONE : View.VISIBLE
                isCircularViewGONEViewVISIBLE = ((androidxDatabindingViewDataBindingSafeUnboxIsCircular) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
        }
        if ((dirtyFlags & 0x4bL) != 0) {



                // read androidx.databinding.ViewDataBinding.safeUnbox(direction)
                androidxDatabindingViewDataBindingSafeUnboxDirection = androidx.databinding.ViewDataBinding.safeUnbox(direction);
            if((dirtyFlags & 0x4bL) != 0) {
                if(androidxDatabindingViewDataBindingSafeUnboxDirection) {
                        dirtyFlags |= 0x100L;
                }
                else {
                        dirtyFlags |= 0x80L;
                }
            }
        }
        if ((dirtyFlags & 0x50L) != 0) {
        }
        if ((dirtyFlags & 0x60L) != 0) {
        }
        // batch finished

        if ((dirtyFlags & 0x180L) != 0) {


            if ((dirtyFlags & 0x80L) != 0) {

                    // read (terminal2) + ("-")
                    terminal2JavaLangString = (terminal2) + ("-");


                    // read ((terminal2) + ("-")) + (terminal1)
                    terminal2JavaLangStringTerminal1 = (terminal2JavaLangString) + (terminal1);
            }
            if ((dirtyFlags & 0x100L) != 0) {

                    // read (terminal1) + ("-")
                    terminal1JavaLangString = (terminal1) + ("-");


                    // read ((terminal1) + ("-")) + (terminal2)
                    terminal1JavaLangStringTerminal2 = (terminal1JavaLangString) + (terminal2);
            }
        }

        if ((dirtyFlags & 0x4bL) != 0) {

                // read androidx.databinding.ViewDataBinding.safeUnbox(direction) ? ((terminal1) + ("-")) + (terminal2) : ((terminal2) + ("-")) + (terminal1)
                directionTerminal1JavaLangStringTerminal2Terminal2JavaLangStringTerminal1 = ((androidxDatabindingViewDataBindingSafeUnboxDirection) ? (terminal1JavaLangStringTerminal2) : (terminal2JavaLangStringTerminal1));
        }
        // batch finished
        if ((dirtyFlags & 0x44L) != 0) {
            // api target 1

            this.mboundView4.setVisibility(isCircularViewVISIBLEViewGONE);
            this.mboundView5.setVisibility(isCircularViewGONEViewVISIBLE);
        }
        if ((dirtyFlags & 0x50L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvLine, line);
        }
        if ((dirtyFlags & 0x60L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvSign, sign);
        }
        if ((dirtyFlags & 0x4bL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.tvTerminals, directionTerminal1JavaLangStringTerminal2Terminal2JavaLangStringTerminal1);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): terminal2
        flag 1 (0x2L): terminal1
        flag 2 (0x3L): isCircular
        flag 3 (0x4L): direction
        flag 4 (0x5L): line
        flag 5 (0x6L): sign
        flag 6 (0x7L): null
        flag 7 (0x8L): androidx.databinding.ViewDataBinding.safeUnbox(direction) ? ((terminal1) + ("-")) + (terminal2) : ((terminal2) + ("-")) + (terminal1)
        flag 8 (0x9L): androidx.databinding.ViewDataBinding.safeUnbox(direction) ? ((terminal1) + ("-")) + (terminal2) : ((terminal2) + ("-")) + (terminal1)
        flag 9 (0xaL): androidx.databinding.ViewDataBinding.safeUnbox(isCircular) ? View.VISIBLE : View.GONE
        flag 10 (0xbL): androidx.databinding.ViewDataBinding.safeUnbox(isCircular) ? View.VISIBLE : View.GONE
        flag 11 (0xcL): androidx.databinding.ViewDataBinding.safeUnbox(isCircular) ? View.GONE : View.VISIBLE
        flag 12 (0xdL): androidx.databinding.ViewDataBinding.safeUnbox(isCircular) ? View.GONE : View.VISIBLE
    flag mapping end*/
    //end
}