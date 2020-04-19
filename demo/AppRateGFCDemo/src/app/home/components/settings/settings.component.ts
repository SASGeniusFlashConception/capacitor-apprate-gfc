import {Component, Input, OnInit} from '@angular/core';
import {ModalController} from '@ionic/angular';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {APPRATE_OPTS} from 'capacitor-apprate-gfc';

@Component({
    selector: 'app-settings',
    templateUrl: './settings.component.html',
    styleUrls: ['./settings.component.scss'],
})
export class SettingsComponent implements OnInit {
    @Input() optionAppRate: APPRATE_OPTS;
    public formAppRateSettings: FormGroup;

    constructor(private _modalCtrl: ModalController, private _formBuilder: FormBuilder) {
    }

    ngOnInit() {
        this.formAppRateSettings = this._formBuilder.group({});

        this.formAppRateSettings.addControl('appName', new FormControl(this.optionAppRate.appName, [Validators.required]));
        this.formAppRateSettings.addControl('appPackageId', new FormControl(this.optionAppRate.appPackageId, [Validators.required]));
        this.formAppRateSettings.addControl('storeUrl', new FormControl(this.optionAppRate.storeUrl, [Validators.required]));
        this.formAppRateSettings.addControl('titleAppRate', new FormControl(this.optionAppRate.titleAppRate, [Validators.required]));
        this.formAppRateSettings.addControl('messageAppRate', new FormControl(this.optionAppRate.messageAppRate, [Validators.required]));
        this.formAppRateSettings.addControl('untilPrompt', new FormControl(this.optionAppRate.untilPrompt, [Validators.required]));
        this.formAppRateSettings.addControl('daysUntilPrompt', new FormControl(this.optionAppRate.daysUntilPrompt, [Validators.required]));
        this.formAppRateSettings.addControl('positifButtonStyleText', new FormControl(this.optionAppRate.positifButtonStyle.buttonActionText, [Validators.required]));
        this.formAppRateSettings.addControl('positifButtonStyleColor', new FormControl(this.optionAppRate.positifButtonStyle.buttonActionColor, [Validators.maxLength(7)]));
        this.formAppRateSettings.addControl('netralButtonStyleText', new FormControl(this.optionAppRate.netralButtonStyle.buttonActionText, [Validators.required]));
        this.formAppRateSettings.addControl('netralButtonStyleColor', new FormControl(this.optionAppRate.netralButtonStyle.buttonActionColor, [Validators.maxLength(7)]));
        this.formAppRateSettings.addControl('negatifButtonStyleText', new FormControl(this.optionAppRate.negatifButtonStyle.buttonActionText, [Validators.required]));
        this.formAppRateSettings.addControl('negatifButtonStyleColor', new FormControl(this.optionAppRate.negatifButtonStyle.buttonActionColor, [Validators.maxLength(7)]));
        this.formAppRateSettings.addControl('darkMode', new FormControl(this.optionAppRate.darkMode));
        this.formAppRateSettings.addControl('addAppIcon', new FormControl(this.optionAppRate.addAppIcon));
        this.formAppRateSettings.addControl('backgroundColor', new FormControl(this.optionAppRate.backgroundColor, [Validators.maxLength(7)]));
    }

    selectDarkMode(ev) {
        this.formAppRateSettings.get('darkMode').setValue(ev.detail.checked);
        if (ev.detail.checked) {
            this.formAppRateSettings.get('positifButtonStyleColor').setValue('#1C9B21');
            this.formAppRateSettings.get('netralButtonStyleColor').setValue('#DCD7D7');
            this.formAppRateSettings.get('negatifButtonStyleColor').setValue('#E35A5A');
            this.formAppRateSettings.get('backgroundColor').setValue('#121212');
        } else {
            this.formAppRateSettings.get('positifButtonStyleColor').setValue('#09A495');
            this.formAppRateSettings.get('netralButtonStyleColor').setValue('#B92F2F');
            this.formAppRateSettings.get('negatifButtonStyleColor').setValue('#313030');
            this.formAppRateSettings.get('backgroundColor').setValue('#FFFFFF');
        }
    }

    selectAddIconApp(ev) {
        this.formAppRateSettings.get('addAppIcon').setValue(ev.detail.checked);
    }

    selectStoreUrl(ev) {
        this.formAppRateSettings.get('storeUrl').setValue(ev.detail.value);
    }

    closeSettings() {
        this._modalCtrl.dismiss(null, 'cancel');
    }

    saveSettings() {
        const data = this.formAppRateSettings.getRawValue();
        this._modalCtrl.dismiss(data, 'save');
    }
}
