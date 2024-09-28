package com.dicoding.mycustomroms

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvRoms: RecyclerView
    private val list = ArrayList<Rom>()

    override fun onCreate(savedInstanceState: Bundle?) {
        theme = resources.newTheme().apply {
            applyStyle(R.style.Theme_MyCustomROMs, true)
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvRoms = findViewById(R.id.rv_roms)
        rvRoms.setHasFixedSize(true)

        list.addAll(getListRoms())
        showRecyclerList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutMeIntent = Intent(this@MainActivity, AboutMeActivity::class.java)
                startActivity(aboutMeIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListRoms(): ArrayList<Rom> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<Rom>()

        for (i in dataName.indices) {
            val hero = Rom(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }

        return listHero
    }

    private fun showRecyclerList() {
        rvRoms.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListRomAdapter(list)
        rvRoms.adapter = listHeroAdapter
    }

}