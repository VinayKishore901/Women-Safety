package com.example.ws


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.squareup.seismic.ShakeDetector


class MainActivity : AppCompatActivity(),ShakeDetector.Listener {
    lateinit var player: MediaPlayer
    lateinit var btn_1 :Button
    lateinit var btn2 : Button
    lateinit var num1 : String
    lateinit var num2 : String
    lateinit var num3 :String
    lateinit var btn5 : Button
    lateinit var sos: Button
    var up: Boolean = false
    var down: Boolean = false
    lateinit var locationManager: LocationManager
    private var hasGps = false
    lateinit var btn3 :Button
    private var hasNetwork = false
    private var locationGps: Location? = null
    private  var locationNetwork: Location? = null
    private val PERMISSION_REQUEST = 10
    private var permission = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.SEND_SMS,android.Manifest.permission.INTERNET
    )

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermission(this, permission)) {
            Toast.makeText(this, "Permission already given", Toast.LENGTH_SHORT).show()
        } else {
            requestPermissions(permission, PERMISSION_REQUEST)
        }

        btn_1 = findViewById<Button>(R.id.button_register)
        btn_1.setOnClickListener(View.OnClickListener {
            var intent =Intent(this,MainActivity2::class.java)
            startActivity(intent)
        })
        btn2 = findViewById<Button>(R.id.proile)
        btn2.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        })

        btn3 = findViewById<Button>(R.id.self_defence)
        btn3.setOnClickListener(View.OnClickListener {
            var intent  =Intent(this , MainActivity4::class.java)
            startActivity(intent)
        })

        btn5 = findViewById<Button>(R.id.htu)
        btn5.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        })

        sos= findViewById<Button>(R.id.sos)
        sos.setOnLongClickListener(View.OnLongClickListener {
            hearShake()

            return@OnLongClickListener true

        })



        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(this)
        sd.start(sensorManager)

        player = MediaPlayer.create(this, R.raw.song)

    }


    override fun hearShake() {
        Toast.makeText(this, "Don't shake me, bro!", Toast.LENGTH_SHORT).show()
        player = MediaPlayer.create(this, R.raw.song)

        player.start()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (hasGps || hasNetwork) {
            if (hasGps) {
                Log.d("locationGps", "hasGps")
                if (ActivityCompat.checkSelfPermission(
                                this,
                                android.Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                this,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        5000,
                        0F,
                        object : LocationListener {
                            override fun onLocationChanged(location: Location?) {
                                if (location != null) {
                                    locationGps = location
                                }
                            }

                            override fun onStatusChanged(
                                    provider: String?,
                                    status: Int,
                                    extras: Bundle?
                            ) {
                            }

                            override fun onProviderEnabled(provider: String?) {
                            }

                            override fun onProviderDisabled(provider: String?) {
                            }

                        })
                var LocalGpsLocation =
                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (LocalGpsLocation != null) {
                    locationGps = LocalGpsLocation
                }
            }
            if (hasNetwork) {
                Log.d("locationGps", "hasGps")
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        5000,
                        0F,
                        object : LocationListener {
                            override fun onLocationChanged(location: Location?) {
                                if (location != null) {
                                    locationNetwork = location
                                }
                            }

                            override fun onStatusChanged(
                                    provider: String?,
                                    status: Int,
                                    extras: Bundle?
                            ) {
                            }

                            override fun onProviderEnabled(provider: String?) {
                            }

                            override fun onProviderDisabled(provider: String?) {
                            }

                        })
                var LocalNetworkLocation =
                        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (LocalNetworkLocation != null) {
                    locationGps = LocalNetworkLocation
                }
            }

        }
        val mPrefs: SharedPreferences = getSharedPreferences("numbers", 0)
        num1 = mPrefs.getString("number1","").toString()
        num2 = mPrefs.getString("number2","").toString()
        num3 = mPrefs.getString("number3","").toString()


        locationGps?.let { sendlocation(num1, it) }
        locationGps?.let { sendlocation(num2, it) }
        locationGps?.let { sendlocation(num3, it) }
        val packageManager = packageManager;

        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+num1 +"&text="+" Help me i am in danger \n http://maps.google.com/maps?q=${locationGps?.latitude},${locationGps?.longitude}"))
        intent.setPackage("com.whatsapp");
        startActivity(intent)




    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {

            down = true
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            up = true
        }
        if (up && down) {
            // Two buttons pressed, call your function
            player.stop()


            up = false
            down = false
        }
        return true
    }


    fun sendlocation(phonenumber: String, currentlocation: Location){
        var smsManager = SmsManager.getDefault()
        var smsBody = StringBuffer()
        smsBody.append("http://maps.google.com/maps?q=")
        smsBody.append(currentlocation.latitude)
        smsBody.append(",")
        smsBody.append(currentlocation.longitude)
        smsManager.sendTextMessage(phonenumber,null,smsBody.toString(),null,null)


    }


    fun checkPermission(context: Context, permissionArray: Array<String>): Boolean {
        var allsuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED) {
                allsuccess = false
            }

        }


        return allsuccess
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            var allsuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allsuccess = false
                    var requestAgain =
                            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                                    permissions[i]
                            ))
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                                this,
                                "Go to settinga and enable the permissions",
                                Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
            if (allsuccess) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val whatsAppAppId = "com.whatsapp"
    fun openWhatsAppAndSendMessage(message: String) {

        val packageManager = packageManager;
        try {
            intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            val text = "This is the actual message to send"
//the following line will throw the NameNotFoundException if WhatsApp is not installed in the phone
            val info = packageManager.getPackageInfo(whatsAppAppId, PackageManager.GET_META_DATA)
            intent.`package` = whatsAppAppId
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(intent, "Finish the action with:"));

        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("WhatsApp", "WhatsApp is not installed in this device")
        }

    }



}