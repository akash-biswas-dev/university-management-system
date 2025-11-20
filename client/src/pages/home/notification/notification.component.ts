import {Component} from '@angular/core';
import {Bell, ChevronRight, LucideAngularModule} from 'lucide-angular';


@Component({
  selector:'home-notification',
  templateUrl:'notification.component.html',
  standalone:true,
  imports: [
    LucideAngularModule
  ]
})
export class Notification {

  readonly notifications:NotificationType[] = [
    {
      id: 1,
      title: "Fall 2025 Admission Open",
      description:
        "Applications for undergraduate and graduate programs are now open",
      date: "Sep 10, 2025",
      type: "admission",
    },
    {
      id: 2,
      title: "Research Conference 2025",
      description:
        "Annual research conference featuring latest innovations in technology",
      date: "Oct 15, 2025",
      type: "event",
    },
    {
      id: 3,
      title: "Scholarship Program Launch",
      description:
        "New merit-based scholarship program for outstanding students",
      date: "Sep 20, 2025",
      type: "scholarship",
    },
  ];

  protected readonly Bell = Bell;
  protected readonly ChevronRight = ChevronRight;
}

export type NotificationType = {
  id: number;
  title: string;
  description: string;
  date: string;
  type: 'event'|'admission'| 'scholarship';
}
