package com.dicoding.mycustomroms

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.mycustomroms.databinding.ActivityDetailRomBinding

class DetailRomActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ROM = "extra_rom"
    }

    private lateinit var binding: ActivityDetailRomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailRomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataRom = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Rom>(EXTRA_ROM, Rom::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Rom>(EXTRA_ROM)
        }

        binding.tvRomName.text = dataRom?.name
        binding.tvRomDescription.text = dataRom?.description
        binding.imgRomLogo.setImageResource(dataRom?.photo ?: 0)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Check out this custom ROM!\n\n\n${binding.tvRomName.text}\n\n${binding.tvRomDescription.text}"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}