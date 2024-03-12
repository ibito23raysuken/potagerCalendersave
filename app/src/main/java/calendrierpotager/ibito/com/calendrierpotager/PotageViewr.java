package calendrierpotager.ibito.com.calendrierpotager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ibito-PC on 09/06/2017.
 */
public class PotageViewr extends RelativeLayout {
    TextView tvNom,tvDistance;
    ImageView ivImage;

    public PotageViewr(Context context) {
        super(context);
    }
    public static PotageViewr create(ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(parent.getContext());
        PotageViewr itemView = (PotageViewr) li.inflate(R.layout.potageview, parent, false);
        itemView.findViews();
        return itemView;
    }
    private void findViews() {
        tvNom = (TextView) findViewById(R.id.item_titleTextView);
        tvDistance = (TextView) findViewById(R.id.item_descriptionTextView);
        ivImage = (ImageView) findViewById(R.id.item_imageView);
    }
    public void display(final Potager potager) {
        tvNom.setText("ici");
        tvDistance.setText("ici");
        ivImage.setImageResource(R.drawable.choux_fleur);
    }


}
