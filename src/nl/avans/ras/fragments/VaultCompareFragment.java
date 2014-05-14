package nl.avans.ras.fragments;

import java.util.ArrayList;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import nl.avans.ras.R;
import nl.avans.ras.model.Vault;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class VaultCompareFragment extends Fragment {

	// Fields
	private ArrayList<Vault> vaultCollection = new ArrayList<Vault>();
	
	// Setters
	public void setVaultCollection(ArrayList<Vault> vaultCollection) {
		if (vaultCollection != null) {
			this.vaultCollection = vaultCollection;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_vault_compare, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {  
      	      new GraphViewData(1, 40)  
      	      , new GraphViewData(2, 12)  
      	      , new GraphViewData(3, 7)
      	      , new GraphViewData(2, 8)
      	      , new GraphViewData(2, 10)
      	      , new GraphViewData(3, 26)
      	      , new GraphViewData(1, 37)
      	      , new GraphViewData(1, 53)
      	      , new GraphViewData(3, 253)

      	});  
      	  
      	GraphView graphView = new LineGraphView(  
      	      getActivity() // context  
      	      , "Job Status Graph" // heading  
      	);  
      	graphView.addSeries(exampleSeries); // data  
      	  
      	LinearLayout layout = (LinearLayout) getActivity().findViewById(R.id.graph_container);  
      	layout.addView(graphView); 
	}
}
