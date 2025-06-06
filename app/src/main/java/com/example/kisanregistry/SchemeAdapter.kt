import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kisanregistry.R
import com.example.kisanregistry.models.Scheme

class SchemeAdapter(private val schemeList: List<Scheme>) :
    RecyclerView.Adapter<SchemeAdapter.SchemeViewHolder>() {

    class SchemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.schemeTitle)
        val description: TextView = itemView.findViewById(R.id.schemeDescription)
        val category: TextView = itemView.findViewById(R.id.schemeCategory)
        val eligibility: TextView = itemView.findViewById(R.id.schemeEligibility)
        val btnDetails: Button = itemView.findViewById(R.id.btnDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_scheme_card, parent, false)
        return SchemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchemeViewHolder, position: Int) {
        val scheme = schemeList[position]
        holder.title.text = scheme.title
        holder.description.text = scheme.description
        holder.category.text = "Category: ${scheme.category}"
        holder.eligibility.text = "Eligibility: ${scheme.eligibility}"
        holder.btnDetails.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scheme.link))
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = schemeList.size
}
