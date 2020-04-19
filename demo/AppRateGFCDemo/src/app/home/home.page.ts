import {Component} from '@angular/core';
import {ModalController, ToastController} from '@ionic/angular';
import {DeviceInfo, Plugins} from '@capacitor/core';
import {SettingsComponent} from './components/settings/settings.component';
import {BehaviorSubject, Observable} from 'rxjs';
import {
    APP_PUBLISH_STATUT,
    APPRATE_INIT,
    APPRATE_OPTS,
    APPRATE_STATUT,
    MARKET_URL
} from 'capacitor-apprate-gfc';

const {AppRateGFC, Device, Toast} = Plugins;

@Component({
    selector: 'app-home',
    templateUrl: 'home.page.html',
    styleUrls: ['home.page.scss'],
})
export class HomePage {
    private _optionAppRate: APPRATE_OPTS;
    private _infoDevice: BehaviorSubject<DeviceInfo>;
    private _deviceInfo: DeviceInfo;
    private _platform: string;

    public get infoDevice(): Observable<DeviceInfo> {
        return this._infoDevice.asObservable();
    }

    constructor(private _modalCtrl: ModalController, private _toastCtrl: ToastController) {
        this._infoDevice = new BehaviorSubject(null);
        const info = this.getInfo();
        info.then(value => this._infoDevice.next(value));
        this.infoDevice.subscribe((infos: DeviceInfo) => {
            this._deviceInfo = infos;
            this._platform = this._deviceInfo ? this._deviceInfo.platform : 'web';
        });
        this._optionAppRate = {
            appName: 'AppRateGFCDemo',
            appPackageId: 'com.gfc.apprategfcdemo',
            storeUrl: MARKET_URL.GOOGLE_PLAY_MARKET,
            titleAppRate: 'Voudriez-vous noter %@ ?',
            messageAppRate: 'Si vous aimez utiliser %@ , voulez-vous prendre un moment pour l\'évaluer? Il ne faudra pas plus d\'une minute. Merci pour votre aide!',
            untilPrompt: 5,
            daysUntilPrompt: 5,
            positifButtonStyle: {
                buttonActionText: 'Oui',
                buttonActionColor: '#09A495',
            },
            netralButtonStyle: {
                buttonActionText: 'Plus tard',
                buttonActionColor: '#313030',
            },
            negatifButtonStyle: {
                buttonActionText: 'Non',
                buttonActionColor: '#B92F2F',
            },
            darkMode: false,
            addAppIcon: false,
            backgroundColor: '#FFFFFF',
        };
        AppRateGFC.addListener('appIsPublishEvent',
            (statut: APP_PUBLISH_STATUT) => {
                if (statut.appOnPlay) {
                    Toast.show({text: 'App is publish !', duration: 'short', position: 'center'});
                } else {
                    Toast.show({text: 'App is not publish !', duration: 'short', position: 'center'});
                }
            }
        );

        AppRateGFC.addListener('isAllReadyShow',
            (statut: boolean) => {
                if (status) {
                    Toast.show({text: 'All Ready Showing !', duration: 'short', position: 'center'});
                }
            }
        );

        AppRateGFC.addListener('positifRateEvent',
            (statut: APPRATE_STATUT) => {
                Toast.show({text: 'Button Action Positif !', duration: 'short', position: 'center'});
            }
        );

        AppRateGFC.addListener('netralRateEvent',
            (statut: APPRATE_STATUT) => {
                Toast.show({text: 'Button Action Netral !', duration: 'short', position: 'center'});
            }
        );

        AppRateGFC.addListener('negativeRateEvent',
            (statut: APPRATE_STATUT) => {
                Toast.show({text: 'Button Action Negatif !', duration: 'short', position: 'center'});
            }
        );
    }

    private async getInfo() {
        return await Device.getInfo();
    }

    async show() {
        if (this._platform === 'android') {
            AppRateGFC.showDirectly().catch(error => Toast.show({text: error.message, duration: 'long', position: 'bottom'}));
        }
    }

    async openSettings() {
        const settingsModal = await this._modalCtrl.create({
            component: SettingsComponent,
            componentProps: {
                optionAppRate: this._optionAppRate
            }
        });

        await settingsModal.present();

        settingsModal.onDidDismiss().then((modalValue: any) => {
            const data = modalValue.data;
            if (data) {
                this._optionAppRate = {
                    appName: data.appName ? data.appName : null,
                    appPackageId: data.appPackageId ? data.appPackageId : null,
                    storeUrl: data.storeUrl ? data.storeUrl : MARKET_URL.GOOGLE_PLAY_MARKET,
                    titleAppRate: data.titleAppRate ? data.titleAppRate : null,
                    messageAppRate: data.messageAppRate ? data.messageAppRate : null,
                    untilPrompt: data.untilPrompt ? data.untilPrompt : null,
                    daysUntilPrompt: data.daysUntilPrompt ? data.daysUntilPrompt : null,
                    positifButtonStyle: {
                        buttonActionText: data.positifButtonStyleText ? data.positifButtonStyleText : null,
                        buttonActionColor: data.positifButtonStyleColor ? data.positifButtonStyleColor : null,
                    },
                    netralButtonStyle: {
                        buttonActionText: data.netralButtonStyleText ? data.netralButtonStyleText : null,
                        buttonActionColor: data.netralButtonStyleColor ? data.netralButtonStyleColor : null,
                    },
                    negatifButtonStyle: {
                        buttonActionText: data.negatifButtonStyleText ? data.negatifButtonStyleText : null,
                        buttonActionColor: data.negatifButtonStyleColor ? data.negatifButtonStyleColor : null,
                    },
                    darkMode: data.darkMode ? data.darkMode : false,
                    addAppIcon: data.addAppIcon ? data.addAppIcon : false,
                    backgroundColor: data.backgroundColor ? data.backgroundColor : null
                };
                this.init();
            }
        });
    }

    check() {
        if (this._platform === 'android') {
            const optsCheck = {
                appPackageId: this._optionAppRate.appPackageId,
                storeUrl: this._optionAppRate.storeUrl
            };
            AppRateGFC.checkAppStatus(optsCheck).catch(error => Toast.show({text: error.message, duration: 'long', position: 'bottom'}));
        }
    }

    init() {
        if (this._platform === 'android') {
            AppRateGFC.init(this._optionAppRate).then((result: APPRATE_INIT) => {
                if (result.appRateInit) {
                    Toast.show({text: 'AppRateGFC init !', duration: 'short', position: 'center'});
                }
            }).catch(error => Toast.show({text: error.message, duration: 'long', position: 'bottom'}));
        }
    }
}
