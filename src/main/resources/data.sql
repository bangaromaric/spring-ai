-- Insertion des types de démarches
INSERT INTO demandes (nom, description) VALUES
                                            ('Passeport', 'Démarche pour obtenir un passeport gabonais.'),
                                            ('Carte d''identite', 'Démarche pour obtenir une carte nationale d’identité.'),
                                            ('Permis de conduire', 'Démarche pour obtenir un permis de conduire.'),
                                            ('Extrait de naissance', 'Démarche pour obtenir un extrait d’acte de naissance.'),
                                            ('Casier judiciaire', 'Demande d’un casier judiciaire.');

-- Insertion des documents requis pour chaque démarche
INSERT INTO document_requis (nom, demande_id) VALUES
                                                   ('Acte de naissance', 1), ('Justificatif de domicile', 1), ('Photos d’identité', 1),
                                                   ('Certificat de nationalité', 2), ('Justificatif de domicile', 2), ('Photo d’identité', 2),
                                                   ('Fiche d’examen médical', 3), ('Justificatif de domicile', 3), ('Test de conduite', 3),
                                                   ('Copie du registre de naissance', 4), ('Timbre fiscal', 4),
                                                   ('Casier judiciaire vierge', 5), ('CNI ou Passeport', 5);
