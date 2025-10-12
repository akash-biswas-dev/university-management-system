import {Component, HostBinding, HostListener} from '@angular/core';
import {HeroComponent} from './hero/hero.component';
import {About} from './about/about.component';
import {Notification} from './notification/notification.component';
import {Programs} from './programs/programs.component';
import {Events} from './events/events.component';
import {Globe, LucideAngularModule, Mail, MapPin, Phone} from 'lucide-angular';


@Component({
  selector: 'home-page',
  templateUrl: 'home.page.html',
  imports: [
    HeroComponent,
    About,
    Notification,
    Programs,
    Events,
    LucideAngularModule
  ],
  standalone: true
})
export class HomePage {
  @HostBinding('class')
  styles = 'min-h-screen bg-gray-50';

  protected readonly Globe = Globe;
  protected readonly Mail = Mail;
  protected readonly MapPin = MapPin;
  protected readonly Phone = Phone;
}
