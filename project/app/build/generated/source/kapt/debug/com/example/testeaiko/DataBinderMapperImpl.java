package com.example.testeaiko;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.testeaiko.databinding.ArrivalsVehicleCardBindingImpl;
import com.example.testeaiko.databinding.BottomSheetLayoutBindingImpl;
import com.example.testeaiko.databinding.FragmentMapBindingImpl;
import com.example.testeaiko.databinding.LineSearchResultCardBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ARRIVALSVEHICLECARD = 1;

  private static final int LAYOUT_BOTTOMSHEETLAYOUT = 2;

  private static final int LAYOUT_FRAGMENTMAP = 3;

  private static final int LAYOUT_LINESEARCHRESULTCARD = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.testeaiko.R.layout.arrivals_vehicle_card, LAYOUT_ARRIVALSVEHICLECARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.testeaiko.R.layout.bottom_sheet_layout, LAYOUT_BOTTOMSHEETLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.testeaiko.R.layout.fragment_map, LAYOUT_FRAGMENTMAP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.testeaiko.R.layout.line_search_result_card, LAYOUT_LINESEARCHRESULTCARD);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ARRIVALSVEHICLECARD: {
          if ("layout/arrivals_vehicle_card_0".equals(tag)) {
            return new ArrivalsVehicleCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for arrivals_vehicle_card is invalid. Received: " + tag);
        }
        case  LAYOUT_BOTTOMSHEETLAYOUT: {
          if ("layout/bottom_sheet_layout_0".equals(tag)) {
            return new BottomSheetLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for bottom_sheet_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMAP: {
          if ("layout/fragment_map_0".equals(tag)) {
            return new FragmentMapBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_map is invalid. Received: " + tag);
        }
        case  LAYOUT_LINESEARCHRESULTCARD: {
          if ("layout/line_search_result_card_0".equals(tag)) {
            return new LineSearchResultCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for line_search_result_card is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(10);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "accessible");
      sKeys.put(2, "arrivalTime");
      sKeys.put(3, "direction");
      sKeys.put(4, "isCircular");
      sKeys.put(5, "line");
      sKeys.put(6, "prefix");
      sKeys.put(7, "sign");
      sKeys.put(8, "terminal1");
      sKeys.put(9, "terminal2");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/arrivals_vehicle_card_0", com.example.testeaiko.R.layout.arrivals_vehicle_card);
      sKeys.put("layout/bottom_sheet_layout_0", com.example.testeaiko.R.layout.bottom_sheet_layout);
      sKeys.put("layout/fragment_map_0", com.example.testeaiko.R.layout.fragment_map);
      sKeys.put("layout/line_search_result_card_0", com.example.testeaiko.R.layout.line_search_result_card);
    }
  }
}
