import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.model.dto.CartItemDto
import com.example.proyectodemoviles.recycler.MyViewCart

    // Esta clase representa una fila del RecyclerView, que contiene los controles necesarios para mostrar un producto en el carrito.
class CartAdapter(

        // La lista de productos que se mostrará en el RecyclerView.
    private var items: List<CartItemDto>,
        // Las funciones que se llamarán cuando se pulse un botón de acción en la fila.
    private val onSumar: (CartItemDto) -> Unit,
    private val onRestar: (CartItemDto) -> Unit,
    private val onEliminar: (CartItemDto) -> Unit

    // Indicamos que usaremos MyViewCart como ViewHolder
) : RecyclerView.Adapter<MyViewCart>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewCart {
        // Se llama cuando el RecyclerView necesita crear una nueva vista (fila) desde cero.
        // Aquí inflamos el layout XML que representa una fila del carrito.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_cart, parent, false)

        // Devolvemos un nuevo ViewHolder (MyViewCart), pasando la vista inflada.
        // Esto permitirá que onBindViewHolder() luego rellene sus datos.
        return MyViewCart(view)
    }



//    Es el método que se llama cada vez que el RecyclerView quiere mostrar un elemento de la lista en pantalla.
//    Se encarga de rellenar la fila con los datos correspondientes al producto (CartItemDto).
    override fun onBindViewHolder(holder: MyViewCart, position: Int) {
        // Obtenemos el producto actual a partir de la posición en la lista.
        val item = items[position]

    // Asignamos los datos del producto a los controles de la fila
        holder.productoNombre.text = item.productName
        holder.productoCantidad.text = item.quantity.toString()
        holder.productoPrecio.text = "€ %.2f".format(item.unitPrice)
        holder.productoSubtotal.text = "Subtotal: € %.2f".format(item.subtotal)

    // Cargamos la imagen del producto desde una URL usando Glide
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.broken_image)
            .into(holder.productoImagen)
    // Evento para cuando se hace clic en el botón de sumar
        holder.btnSumar.setOnClickListener {
            onSumar(item)
        }

        holder.btnRestar.setOnClickListener {
            if (item.quantity > 1) {
                onRestar(item)
            } else {
                Toast.makeText(holder.itemView.context, "Cantidad mínima: 1", Toast.LENGTH_SHORT).show()
            }
        }
        // Evento para cuando se hace clic en el botón de eliminar un producto del carrito
        holder.iconDelete.setOnClickListener {
            onEliminar(item)
        }
    }
    // Devuelve el número de elementos que contiene la lista.
    override fun getItemCount(): Int = items.size

    // Actualiza los datos de la lista y notifica a los observers que se han modificado.
    fun updateData(newItems: List<CartItemDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}