package com.example.iji.api

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context

object AccountHelper {
    private var accountManager: AccountManager? = null
    private const val MY_ACCOUNT_TYPE = "com.example.IjI.login"

    fun initAccountManager(context: Context) {
        accountManager = AccountManager.get(context)
    }
    fun getAccountManager(): AccountManager? = accountManager

    fun addAccount(email: String, password: String){
        Account(email, MY_ACCOUNT_TYPE).also { account ->
            accountManager?.addAccountExplicitly(account, password, null)
        }
    }
}