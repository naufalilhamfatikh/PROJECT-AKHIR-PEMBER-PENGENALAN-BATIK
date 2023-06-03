package com.example.projectakhirpraktikum

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class akun : Fragment() {

    private lateinit var textViewUser: TextView
    private lateinit var textViewID: TextView
    private lateinit var textViewName: TextView
    private lateinit var textViewUmur: TextView
    private lateinit var textViewAlamat: TextView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewPassword: TextView
    private lateinit var userDAO: UserDAO
    private var username: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userDAO = UserDAO(context)
        userDAO.open()
    }

    override fun onDetach() {
        super.onDetach()
        userDAO.close()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_akun, container, false)

        textViewUser = view.findViewById(R.id.login_sebagai_username)
        textViewID = view.findViewById(R.id.id_ID)
        textViewName = view.findViewById(R.id.id_name)
        textViewUmur = view.findViewById(R.id.idumur)
        textViewAlamat = view.findViewById(R.id.idalamat)
        textViewUsername = view.findViewById(R.id.idusername)
        textViewPassword = view.findViewById(R.id.idpassword)

        // Mengambil nilai username dari argument yang diterima
        username = arguments?.getString("usernameUser")

        // Mengambil data pengguna dari database berdasarkan username
        val user = userDAO.getUserByUsername(username ?: "")

        // Set nilai pada TextView berdasarkan data pengguna yang ditemukan
        textViewID.text = "ID: ${user?.id}"
        textViewName.text = "Name: ${user?.name}"
        textViewUmur.text = "Umur: ${user?.umur} Tahun"
        textViewAlamat.text = "Alamat: ${user?.alamat}"
        textViewUsername.text = "Username: ${user?.username}"
        textViewUser.text = "${user?.username}"
        textViewPassword.text = "Password: ${user?.password}"

        return view
    }
    companion object {
        fun newInstance(username: String?): akun {
            val fragment = akun()
            val bundle = Bundle()
            bundle.putString("usernameUser", username)
            fragment.arguments = bundle
            return fragment
        }
    }

}

