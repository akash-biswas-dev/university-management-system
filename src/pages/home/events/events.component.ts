import {Component} from '@angular/core';
import {Calendar, Clock, LucideAngularModule, MapPin} from 'lucide-angular';

@Component({
  selector: 'home-events',
  templateUrl: 'events.component.html',
  imports: [
    LucideAngularModule
  ],
  standalone: true
})
export class Events{

  events = [
    {
      title: "Tech Innovation Summit",
      date: "October 22, 2025",
      time: "9:00 AM - 5:00 PM",
      location: "Main Auditorium",
      category: "Conference",
    },
    {
      title: "Career Fair 2025",
      date: "November 5, 2025",
      time: "10:00 AM - 4:00 PM",
      location: "Sports Complex",
      category: "Career",
    },
    {
      title: "International Student Welcome",
      date: "September 25, 2025",
      time: "2:00 PM - 6:00 PM",
      location: "Student Center",
      category: "Social",
    },
  ];
  protected readonly Calendar = Calendar;
  protected readonly Clock = Clock;
  protected readonly MapPin = MapPin;
}

export type EventsType = {
  title: string,
  time: string,
  date: string,
  location: string,
  category: string,
}
