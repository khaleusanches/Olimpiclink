﻿using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Events
{
    public class EventModel
    {
        [Key]
        public int idEvent { get; set; }
        public int place_id { get; set; }
        public int leader_id { get; set; }
        public int comunity_id { get; set; }
        public string nameEvent { get; set; }
        public string? endereco { get; set; }
        public string descriptionEvent { get; set; }
        public DateTime dateTimeEvent { get; set; }
        public DateTime closingDateTimeEvent { get; set; }
        public DateTime? created_at_event { get; set; }
        public DateTime? updated_at_event { get; set; }
        public bool activated_event { get; set; }

        public EventModel(
            int place_id, 
            int comunity_id, 
            int leader_id,
            string nameEvent, 
            string descriptionEvent, 
            DateTime dateTimeEvent, 
            DateTime closingDateTimeEvent,
            string endereco
            )
        {
            this.place_id = place_id;
            this.comunity_id = comunity_id;
            this.leader_id = leader_id;
            this.nameEvent = nameEvent;
            this.descriptionEvent = descriptionEvent;
            this.dateTimeEvent = dateTimeEvent;
            this.closingDateTimeEvent = closingDateTimeEvent;
            this.endereco = endereco;
            var teste = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            created_at_event = DateTime.ParseExact(teste, "yyyy-MM-dd HH:mm:ss", null);
            updated_at_event = DateTime.ParseExact(teste, "yyyy-MM-dd HH:mm:ss", null);
            activated_event = true;
        }
    }
}
