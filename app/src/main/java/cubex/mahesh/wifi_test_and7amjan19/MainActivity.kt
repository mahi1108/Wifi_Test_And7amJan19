package cubex.mahesh.wifi_test_and7amjan19

import android.app.Activity
import android.content.Context
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wManager  = applicationContext.getSystemService(
            Context.WIFI_SERVICE) as WifiManager
        var wstate = wManager.wifiState
        when(wstate)
        {
            0 -> switch1.isChecked = false
            1 ->  switch1.isChecked = false
            2 ->  switch1.isChecked = true
            3 ->  switch1.isChecked = true
        }

        switch1.setOnCheckedChangeListener { compoundButton, b ->
                if(b)
                    wManager.setWifiEnabled(true)
                else
                    wManager.setWifiEnabled(false)
        }

        gwd.setOnClickListener {
            var temp_list = mutableListOf<String>()
            var list = wManager.scanResults
            for(device in list)
            {
                temp_list.add("Name : ${device.SSID} \n Frequency : ${device.frequency}")
            }
            var adapter = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_list_item_single_choice,
                temp_list)
            lview.adapter = adapter
        }

        gpd.setOnClickListener {
            var temp_list = mutableListOf<String>()
            var list = wManager.configuredNetworks
            for(device in list)
            {
                temp_list.add("Name : ${device.SSID} \n Status : ${device.status}")
            }
            var adapter = ArrayAdapter<String>(
                this@MainActivity,
                android.R.layout.simple_list_item_single_choice,
                temp_list)
            lview.adapter = adapter

        }


    }
}
