package nl.avans.ras.fragments;

import nl.avans.ras.R;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;

public class SpeedChartFragment extends ChartFragment {

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		GraphView graphView = new LineGraphView(  
	    	      getActivity() // context  
	    	      , "Distance" 	// heading  
	    	);
	    	
		// create graphviews
		for (int x = 0; x < vaultCollection.size(); x++) {
			double[] graphData = vaultCollection.get(x).getSpeedGraphData();
			GraphViewData[] dataContainer = new GraphViewData[graphData.length];
			for (int y = 0; y < graphData.length; y++) {
				dataContainer[y] = new GraphViewData(y, graphData[y] + (x * 5));
			}
			GraphViewSeries serie = new GraphViewSeries(dataContainer);
			serie.getStyle().color = getColor(x) != -1 ? getColor(x) : Color.BLUE;
			graphView.addSeries(serie);
		}
		
		// Set the style of the graphview
		graphView.getGraphViewStyle().setGridColor(Color.BLACK);
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.BLACK);
		graphView.getGraphViewStyle().setTextSize(getResources().getDimension(R.dimen.medium_font));
		graphView.getGraphViewStyle().setNumHorizontalLabels(5);
		graphView.getGraphViewStyle().setNumVerticalLabels(10);
		  	  
		if (vaultCollection != null && vaultCollection.size() > 1) {
			graphView.setShowLegend(true);
	    	graphView.setLegendAlign(LegendAlign.BOTTOM);
	        graphView.setLegendWidth(200);
	    }
		  	
	  	RelativeLayout layout = (RelativeLayout) getView().findViewById(R.id.graph_container);  
	  	layout.addView(graphView);
	}
}
