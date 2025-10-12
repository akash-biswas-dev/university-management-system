import {Component, HostBinding} from '@angular/core';
import {ArrowRight, Award, BookOpen, Globe, LucideAngularModule, Users} from 'lucide-angular';
import {RouterOutlet} from '@angular/router';
import {NavbarComponent} from '../../../components/navbar/navbar.component';
import {Button} from '../../../components/button/button.compoenent';


@Component({
  selector: 'home-hero',
  templateUrl: 'hero.component.html',
  standalone: true,
  imports: [LucideAngularModule, NavbarComponent, Button]
})
export class HeroComponent {

  // Icons
  readonly Award = Award;
  readonly ArrowRight = ArrowRight;
  readonly BookOpen = BookOpen;
  readonly Users = Users;
  readonly Globe = Globe;

}
