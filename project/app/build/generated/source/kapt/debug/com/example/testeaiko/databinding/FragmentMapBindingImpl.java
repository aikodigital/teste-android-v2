package com.example.testeaiko.databinding;
import com.example.testeaiko.R;
import com.example.testeaiko.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentMapBindingImpl extends FragmentMapBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(7);
        sIncludes.setIncludes(0, 
            new String[] {"bottom_sheet_layout"},
            new int[] {1},
            new int[] {com.example.testeaiko.R.layout.bottom_sheet_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.mapView, 2);
        sViewsWithIds.put(R.id.searchCardView, 3);
        sViewsWithIds.put(R.id.searchView, 4);
        sViewsWithIds.put(R.id.rv_search_filters, 5);
        sViewsWithIds.put(R.id.btn_location, 6);
    }
    // views
    @NonNull
    private final androidx.coordinatorlayout.widget.CoordinatorLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentMapBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }
    private FragmentMapBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (com.example.testeaiko.databinding.BottomSheetLayoutBinding) bindings[1]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[6]
            , (com.google.android.gms.maps.MapView) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[5]
            , (androidx.cardview.widget.CardView) bindings[3]
            , (androidx.appcompat.widget.SearchView) bindings[4]
            );
        setContainedBinding(this.bottomSheet);
        this.mboundView0 = (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        bottomSheet.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (bottomSheet.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        bottomSheet.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeBottomSheet((com.example.testeaiko.databinding.BottomSheetLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeBottomSheet(com.example.testeaiko.databinding.BottomSheetLayoutBinding BottomSheet, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        // batch finished
        executeBindingsOn(bottomSheet);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): bottomSheet
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}