package nl.avans.ras.fragments;

import java.util.ArrayList;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import nl.avans.ras.R;
import nl.avans.ras.model.Vault;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ChartFragment extends Fragment {

	// Fields
	private ArrayList<Vault> vaultCollection = new ArrayList<Vault>();
	public static final String ARG_PAGE = "page";
    private int mPageNumber;
    
	// Setters
	public void setVaultCollection(ArrayList<Vault> vaultCollection) {
		if (vaultCollection != null) {
			this.vaultCollection = vaultCollection;
		}
	}
	
	public static ChartFragment create(int pageNumber) {
		ChartFragment fragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_chart, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		GraphViewSeries exampleSeries = null;
		if (vaultCollection != null && vaultCollection.size() > 1) {
			exampleSeries = new GraphViewSeries(new GraphViewData[] {  
					new GraphViewData(0, 0)
		      	  , new GraphViewData(1, 1)   
	      	      , new GraphViewData(2, 3)  
	      	      , new GraphViewData(3, 7)
	      	      , new GraphViewData(4, 10)
	      	      , new GraphViewData(5, 13)
	      	      , new GraphViewData(6, 16)
	      	      , new GraphViewData(7, 19)
	      	      , new GraphViewData(8, 21)
	      	      , new GraphViewData(9, 23)
	      	      , new GraphViewData(10, 25)  
	      	      , new GraphViewData(11, 27)
	      	      , new GraphViewData(12, 30)
	      	      , new GraphViewData(13, 30.5)
	      	      , new GraphViewData(14, 31)
	      	      , new GraphViewData(15, 31)
	      	      , new GraphViewData(16, 31)
	      	      , new GraphViewData(17, 29)
	      	      , new GraphViewData(18, 27)
	      	      , new GraphViewData(19, 25)
	      	      , new GraphViewData(20, 20)
	      	}); 
		}
		
		GraphViewSeries exampleSeries2 = new GraphViewSeries(new GraphViewData[] {
				new GraphViewData(0, 0)
	     	  , new GraphViewData(1, 1)  
	     	  , new GraphViewData(2, 3)  
	      	  , new GraphViewData(3, 6)
	      	  , new GraphViewData(4, 9)
	      	  , new GraphViewData(5, 11)
	      	  , new GraphViewData(6, 13)
	      	  , new GraphViewData(7, 16)
	      	  , new GraphViewData(8, 19)
	      	  , new GraphViewData(9, 23)
	      	  , new GraphViewData(10, 25)  
	      	  , new GraphViewData(11, 30)
      	      , new GraphViewData(12, 32)
      	      , new GraphViewData(13, 33.5)
      	      , new GraphViewData(14, 34)
      	      , new GraphViewData(15, 34)
      	      , new GraphViewData(16, 34)
      	      , new GraphViewData(17, 33.5)
      	      , new GraphViewData(18, 30)
      	      , new GraphViewData(19, 28)
      	      , new GraphViewData(20, 26)
      	});
      	  
      	GraphView graphView = new LineGraphView(  
      	      getActivity() // context  
      	      , "Speed" 	// heading  
      	);  
      	
      	if (vaultCollection != null && vaultCollection.size() > 1) {
      		graphView.addSeries(exampleSeries); // data
      	}
      	graphView.addSeries(exampleSeries2); // data
      	graphView.setViewPort(0, 15);
      	graphView.setScrollable(true);
      	graphView.setShowLegend(true);
      	graphView.setLegendAlign(LegendAlign.BOTTOM);
      	graphView.setLegendWidth(200);
      	
      	// Set the style of the graphview
      	graphView.getGraphViewStyle().setGridColor(Color.BLACK);
      	graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
      	graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
      	graphView.getGraphViewStyle().setTextSize(getResources().getDimension(R.dimen.medium_font));
      	graphView.getGraphViewStyle().setNumHorizontalLabels(5);
      	graphView.getGraphViewStyle().setNumVerticalLabels(10);
      	  
      	RelativeLayout layout = (RelativeLayout) getActivity().findViewById(R.id.graph_container);  
      	layout.addView(graphView); 
	}
}
