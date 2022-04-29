import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.goposrecruitmenttask.R
import database.ItemEntity


class ItemAdapter(context: Context?, items: ArrayList<ItemEntity>) :
    ArrayAdapter<ItemEntity?>(context!!, 0, items as List<ItemEntity?>) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView: View? = convertView
        val item: ItemEntity? = getItem(position)

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        }

        val itemName = convertView?.findViewById(R.id.itemName) as TextView
        val itemPrice = convertView.findViewById(R.id.itemPrice) as TextView
        val vat = convertView.findViewById(R.id.vat) as TextView
        val category = convertView.findViewById(R.id.category) as TextView
        val image = convertView.findViewById(R.id.itemIcon) as ImageView

        itemName.text = "${context.getString(R.string.name)} ${item?.name}"
        itemPrice.text = "${context.getString(R.string.price)} ${item?.price}"
        vat.text = "${context.getString(R.string.vat)} ${item?.taxRate}"
        category.text = "${context.getString(R.string.category)} ${item?.category}"
        getImageFromUrl(item!!.image_link, image)

        Log.i("Test",item.toString())

        return convertView
    }

    fun getImageFromUrl(url: String, imageView: ImageView) {
        if (!url.equals("empty")){
            Glide.with(context)
                .load(url)
                .into(imageView)
        }else{
            imageView.setImageResource(0)
        }
    }
}