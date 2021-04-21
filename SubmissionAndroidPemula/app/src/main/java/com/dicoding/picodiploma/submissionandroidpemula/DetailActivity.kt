package com.dicoding.picodiploma.submissionandroidpemula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var tvUsername: TextView = findViewById(R.id.tv_username)
        var tvName: TextView = findViewById(R.id.tv_Name)
        var tvLocation : TextView = findViewById(R.id.tv_location)
        var tvCompany : TextView = findViewById(R.id.tv_company)
        var tvRepo : TextView = findViewById(R.id.tv_repository)
        var tvFollower : TextView = findViewById(R.id.tv_follower)
        var tvFollowing : TextView = findViewById(R.id.tv_following)
        var imgDetail: ImageView = findViewById(R.id.img_detail)


        val person = intent.getParcelableExtra<Character>("Bebas") as Character

        tvUsername.setText("@"+person.username)
        tvName.setText(person.name)
        Glide.with(this)
            .load(person.photo)
            .apply(RequestOptions())
            .into(imgDetail)
        tvLocation.setText(person.location)
        tvRepo.setText(person.repository)
        tvCompany.setText(person.company)
        tvFollower.setText(person.followers)
        tvFollowing.setText(person.following)
        Toast.makeText(this, person.name,Toast.LENGTH_LONG).show()

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = person.name
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}