import {Component, HostBinding} from '@angular/core';
import {Award, Globe, LucideAngularModule, Users} from 'lucide-angular';


@Component({
  selector: 'home-about',
  templateUrl:'about.component.html',
  standalone: true,
  imports:[
    LucideAngularModule
  ]
})
export class About{

  protected readonly Award = Award;
  protected readonly Users = Users;
  protected readonly Globe = Globe;

}
