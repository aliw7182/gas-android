package kz.kaztransgas.kaztransgas.ui.MainScreen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import kz.kaztransgas.kaztransgas.databinding.ScanScreenBinding
import kz.kaztransgas.kaztransgas.utils.AbstractFragment
import kz.kaztransgas.kaztransgas.utils.FragmentReference
import kotlin.properties.Delegates

class ScanScreen : AbstractFragment() {

    private var _binding: ScanScreenBinding? = null
    private val binding get() = _binding!!

    private val PERMISSION_REQUEST_CAMERA = 100

    private var mCameraSource by Delegates.notNull<CameraSource>()
    private var textRecognizer by Delegates.notNull<TextRecognizer>()
    private var text:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ScanScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCameraSource()
        text?.let {
            navigateToMain(text)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun isCameraPermissionGranted(): Boolean {
        context?.let {
            return ContextCompat.checkSelfPermission(it,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

        }
        return false
    }

    private fun requestForPermission() {
        requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CAMERA
            )

        }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode != PERMISSION_REQUEST_CAMERA) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (isCameraPermissionGranted()) {
                mCameraSource.start(binding.surfaceCameraPreview.holder)
            } else {
                Toast.makeText(context, "Permission need to grant", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun startCameraSource() {
        //  Create text Recognizer
        context?.let {

            textRecognizer = TextRecognizer.Builder(it).build()
            if (!textRecognizer.isOperational) {
                Log.d("scan", "Dependencies are downloading....try after few moment")
                return
            }
//  Init camera source to use high resolution and auto focus
            mCameraSource = CameraSource.Builder(it, textRecognizer)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1280, 1024)
                .setAutoFocusEnabled(true)
                .setRequestedFps(2.0f)
                .build()
            binding.surfaceCameraPreview.holder.addCallback(object : SurfaceHolder.Callback {

                @SuppressLint("MissingPermission")
                override fun surfaceCreated(holder: SurfaceHolder) {
                    try {
                        if (isCameraPermissionGranted()) {
                            mCameraSource.start(binding.surfaceCameraPreview.holder)
                        } else {
                            requestForPermission()
                        }
                    } catch (e: Exception) {
                    }
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder,
                    format: Int,
                    width: Int,
                    height: Int
                ) {
                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    mCameraSource.stop()
                }
            })

            textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
                override fun release() {}

                override fun receiveDetections(detections: Detector.Detections<TextBlock>) {
                    val items = detections.detectedItems

                    if (items.size() <= 0) {
                        return
                    }


                    val stringBuilder = StringBuilder()
                    for (i in 0 until items.size()) {
                        val item = items.valueAt(i)
                        stringBuilder.append(item.value)
                        stringBuilder.append("\n")
                        break
                    }
                    Log.d("scan",stringBuilder.toString())
                    if(stringBuilder.isNotEmpty()){
                        text = stringBuilder.toString()
                        mCameraSource.stop()
                        navigateToMain(text)
                        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()

                    }
                }
            })


        }
    }

    private fun navigateToMain(text:String?){
        val bundle = Bundle()
        bundle.putString("number",text)
        fragmentNavigator
            .navigateTo(FragmentReference.MAIN_SCREEN)
            .isRoot(true)
            .setBundle(bundle)
            .navigate()
    }
}