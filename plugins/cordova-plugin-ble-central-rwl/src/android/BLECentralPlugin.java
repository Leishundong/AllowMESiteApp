// (c) 2014-2016 Don Coleman
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.megster.cordova.ble.central;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.json.JSONException;


import android.os.Message;
import android.util.Log;

import java.util.*;


public class BLECentralPlugin extends CordovaPlugin {
  // actions
  private static final String PRINT = "print";
  public static final String QSPRINTER_NAME = "QSprinter";
  private static final int REQUEST_ENABLE_BT = 2;
  public static final int INTEVAL = 20;//20秒


  BlueToothService mService = null;
  CallbackContext callbackContext = null;


  String msgToPrint = null;
  boolean callPrintInProgress = false;
  boolean alreadyHaveOnePrinterToConnect = false;//当前是否有可以连接的打印机了。

  //message
  private final static String ERROR_MSG_callPrintInProgress = "正在打印，请不要连续按键！";
  private final static String ERROR_MSG_QSPrinterproblem = "打印机故障，请换一台试试?";
  private final static String ERROR_MSG_QSPrinterNotFound = "没找到打印机，请确认打印机已开机!";
  private final static String ERROR_MSG_DISCONNECT = "手机蓝牙开关重启下，再重启打印机，然后稍等10秒钟再打印！";
  public static final String ERRO_MSG_TIMEOUT = "超过20秒啦，请把手机蓝牙开关重启下，再重启打印机，然后稍等10秒钟再打印！";

  private static final String MESSGE_PRINT_CANCELED = "打印被取消了";//打印取消了
  private Timer timer;
  private TimerTask timertask;

  private void callback(boolean success, String message) {
    if (ERROR_MSG_callPrintInProgress.equals(message)) {
      callbackContext.error(message);
    } else {
      if (this.callPrintInProgress) {
        Log.i("Bluetooth", success + ":" + message);
        reset();
        if (success) {
          callbackContext.success();
        } else {
          callbackContext.error(message);
        }
      }
    }
  }

  @Override
  public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
    LOG.d("BLECentralPlugin", "action = " + action);
    boolean validAction = true;
    this.callbackContext = callbackContext;

    if (action.equals(PRINT)) {
      String message = "" + args.getString(0);
      if ((message.length() > 0 && !callPrintInProgress)) {//消息的长度大于0并且没有其他打印调用在执行中
        reset();
        this.callPrintInProgress = true;
        this.msgToPrint = message;
        this.timer=new Timer();
        this.timertask=new TimerTask() {
          @Override
          public void run() {
            callback(false, ERRO_MSG_TIMEOUT);
          }
        };
        timer.schedule(this.timertask, INTEVAL *1000);
        findOrdiscoverIfNecessary();
      } else {
        callback(false, ERROR_MSG_callPrintInProgress);
      }
    } else {
      validAction = false;
    }
    return validAction;
  }


  private void printIfNecessary() {
    if (this.callPrintInProgress && this.alreadyHaveOnePrinterToConnect) {
      try {
        mService.sendMessage(msgToPrint + "\n", "GBK");
        Log.v("QSPrinter===", msgToPrint);
      } catch (Exception e) {
        e.printStackTrace();
        callback(false, ERROR_MSG_QSPrinterproblem);
      }
    }
  }

  public void reset() {
    if (mService != null) {
      mService.cancelDiscovery();
      mService.stop();
      mService = null;
    }
    try {
      webView.getContext().unregisterReceiver(mReceiver);
    } catch (Exception e) {
    }
    this.callPrintInProgress=false;
    this.alreadyHaveOnePrinterToConnect = false;
    if(this.timer!=null){
      this.timer.cancel();
      this.timer=null;
    }
    if(this.timer!=null){
      this.timer.cancel();
      this.timer=null;
    }
    if(this.timertask!=null){
      this.timertask.cancel();
      this.timertask=null;
    }
  }


  private void findOrdiscoverIfNecessary() {
    if (this.mService == null) {
      this.mService = new BlueToothService(null, mHandler);
    }
    if (!this.mService.isAvailable() || !this.mService.isBTopen()) {
      //蓝牙未开…
      Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      cordova.startActivityForResult(this, enableIntent, REQUEST_ENABLE_BT);
    } else {//链接QSPrinter// 1注册receiver
      boolean qsprinterpaired = false;//是否已有配对的打印机
      Set<BluetoothDevice> pairedDevices = mService.getPairedDev();
      // If there are paired devices, add each one to the ArrayAdapter
      if (pairedDevices.size() > 0) {
        for (BluetoothDevice device : pairedDevices) {
          if (QSPRINTER_NAME.equals(device.getName())) {
            qsprinterpaired = true;
            connectPrinter(device);
            break;
          }
        }
      }
      if (!qsprinterpaired) {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        webView.getContext().registerReceiver(mReceiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        webView.getContext().registerReceiver(mReceiver, filter);
        if (mService.isDiscovering()) {//2发现QSPrinter
          mService.cancelDiscovery();
        }
        mService.startDiscovery();
      }

    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    reset();
  }


  // The BroadcastReceiver that listens for discovered devices and
  // changes the title when discovery is finished
  private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      // When discovery finds a device
      if (BluetoothDevice.ACTION_FOUND.equals(action)) {
        // Get the BluetoothDevice object from the Intent
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        // If it's already paired, skip it, because it's been listed already
        if (device.getBondState() != BluetoothDevice.BOND_BONDED && QSPRINTER_NAME.equals(device.getName())) {//
          //获取列表项中设备的mac地址
          connectPrinter(device);
        }
        // When discovery is finished, change the Activity title
      } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
        if (!alreadyHaveOnePrinterToConnect) {
          callback(false, ERROR_MSG_QSPrinterNotFound);
        }
      }
    }
  };

  private void connectPrinter(BluetoothDevice device) {
    BluetoothDevice con_dev = mService.getDevByMac(device.getAddress());
    if (!alreadyHaveOnePrinterToConnect) {
      alreadyHaveOnePrinterToConnect = true;
      //真正的连接打印机
      mService.connect(con_dev);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
      case REQUEST_ENABLE_BT:
        //请求打开蓝牙
        if (resultCode == Activity.RESULT_OK) {
          //蓝牙已经打开
          findOrdiscoverIfNecessary();
        } else {
          //用户不允许打开蓝牙
          callback(false, MESSGE_PRINT_CANCELED);
        }
        break;

    }
  }

  /**
   * 创建一个Handler实例，用于接收BluetoothService类返回回来的消息
   */
  private final Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case BlueToothService.MESSAGE_STATE_CHANGE:
          switch (msg.arg1) {
            case BlueToothService.STATE_CONNECTED:
              //已连接
              Log.i("Bluetooth", "Connect successful");
              printIfNecessary();
              break;
            case BlueToothService.STATE_CONNECTING:
              //正在连接
              Log.i("Bluetooth", ".....is connecting");
              break;
            case BlueToothService.STATE_LISTEN:
              callback(false, ERROR_MSG_DISCONNECT);
              break;
            //监听连接的到来
            case BlueToothService.STATE_NONE:
              Log.i("Bluetooth", ".....wait connecting");
              break;
          }
          break;
        case BlueToothService.MESSAGE_WRITE:
          callback(true, null);
          break;
        case BlueToothService.MESSAGE_CONNECTION_LOST:
          //蓝牙已断开连接
          Log.i("Bluetooth", "Device connection was lost");
          break;
        case BlueToothService.MESSAGE_UNABLE_CONNECT:
          //无法连接设备
          Log.i("Bluetooth", "Unable to connect device");
          callback(false, ERROR_MSG_QSPrinterNotFound);
          break;
      }
    }

  };

}
