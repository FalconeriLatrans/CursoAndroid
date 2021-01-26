package com.example.desafiofirebase.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.*
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.desafiofirebase.R
import com.example.desafiofirebase.util.Constants.Codes.IMAGE_PICK_CODE
import com.example.desafiofirebase.util.Constants.Database.DATABASE_NAME
import com.example.desafiofirebase.util.Constants.Database.DB_CREATED_BY
import com.example.desafiofirebase.util.Constants.Database.DB_DESCRIPTION
import com.example.desafiofirebase.util.Constants.Database.DB_ID
import com.example.desafiofirebase.util.Constants.Database.DB_RELEASE
import com.example.desafiofirebase.util.Constants.Database.DB_THUMBNAIL
import com.example.desafiofirebase.util.Constants.Database.DB_TITLE
import com.example.desafiofirebase.util.Constants.Intent.KEY_INTENT_GAME
import com.example.desafiofirebase.util.Game
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*
import kotlin.collections.HashMap

class GameEditActivity : AppCompatActivity() {

    private val game: Game? by lazy { intent.getParcelableExtra(KEY_INTENT_GAME) }

    private val changeImageButton: FrameLayout by lazy { findViewById(R.id.gameEdit_changeImageButton) }
    private val image: ImageView by lazy { findViewById(R.id.gameEdit_image) }
    private val imageContainer: CardView by lazy { findViewById(R.id.gameEdit_imageContainer) }
    private val name: TextInputLayout by lazy { findViewById(R.id.gameEdit_name) }
    private val release: TextInputLayout by lazy { findViewById(R.id.gameEdit_release) }
    private val description: TextInputLayout by lazy { findViewById(R.id.gameEdit_description) }
    private val saveButton: Button by lazy { findViewById(R.id.gameEdit_saveButton) }
    private val randomId: String by lazy { UUID.randomUUID().toString() }
    private var localImageUri = Uri.EMPTY
    private var newImage = false
    private var newGame = false
    private val gameTemp: HashMap<String, String> = HashMap()

    private val firebaseAuth by lazy { Firebase.auth }
    private val firebaseStorage by lazy { Firebase.storage.reference }
    private val firebaseDatabase by lazy { Firebase.firestore }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_edit)

        initComponents()
        setupObservables()
    }

    private fun initComponents() {
        game?.let {
            newGame = false
            it.thumbnail.let { url ->
                imageContainer.visibility = VISIBLE
                Glide.with(this).load(url).into(image)
            }
            name.editText?.setText(it.title)
            release.editText?.setText(it.release)
            description.editText?.setText(it.description)
        } ?: run {
            newGame = true
        }
    }

    private fun setupObservables() {
        changeImageButton.setOnClickListener {
            val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPhoto, IMAGE_PICK_CODE)
        }
        saveButton.setOnClickListener {
            updateDatabase()
            finish()
        }
    }

    private fun updateDatabase() {
        gameTemp[DB_TITLE] = name.editText?.text.toString()
        gameTemp[DB_RELEASE] = release.editText?.text.toString()
        gameTemp[DB_DESCRIPTION] = description.editText?.text.toString()
        gameTemp[DB_CREATED_BY] = firebaseAuth.currentUser.hashCode().toString()
        gameTemp[DB_THUMBNAIL] = game?.thumbnail ?: ""
        if (newGame) {
            gameTemp[DB_ID] = randomId
        } else {
            gameTemp[DB_ID] = game?.id ?: ""
        }

        //Uploading image
        Log.i("GameEditActivity", "LocalImage: ${localImageUri.path}")
        if (newImage) {
            gameTemp[DB_THUMBNAIL] = "Imagem nova"
            val refStorage =
                firebaseStorage.child("images/${gameTemp[DB_ID]}.jpg") //name.editText?.text.toString().replace("\\s".toRegex(), "")
            refStorage.putFile(localImageUri).addOnSuccessListener { upload ->
                Log.i("GameEditActivity", "Upload da imagem feito com sucesso")
                upload.storage.downloadUrl.addOnSuccessListener {
                    Log.i("GameEditActivity", "Criação do link para download feito com sucesso")
                    gameTemp[DB_THUMBNAIL] = it.toString()
                    Log.i("GameEditActivity", "Url da imagem: ${it.toString()}")
                    firebaseDatabase.collection(DATABASE_NAME).document(gameTemp[DB_ID] ?: "")
                        .set(gameTemp, SetOptions.merge())
                        .addOnSuccessListener {
                            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                }
            }
        } else {
            gameTemp[DB_THUMBNAIL] = game?.thumbnail ?: ""
            firebaseDatabase.collection(DATABASE_NAME).document(gameTemp[DB_ID] ?: "")
                .set(gameTemp, SetOptions.merge())
                .addOnSuccessListener {
                    Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            localImageUri = data?.data as Uri
            newImage = true
            Log.i("GameEditActivity", "Path da nova imagem: ${localImageUri}")
            imageContainer.visibility = VISIBLE
            Glide.with(this).load(localImageUri.toString()).into(image)
        }
    }
}