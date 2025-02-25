INSERT INTO centre_service (nom, adresse, contact) VALUES
                                                       ('Siège social de la DGDI', 'zone industrielle d’Oloumi de Libreville', 'Tel : 011 76 24 24 / 011 76 00 24, contact@dgdi.ga'),
                                                       ('Mairie d’Owendo', 'Boulevard des Deux Églises, Owendo', '014567890'),
                                                       ('Ambassade du Gabon en France', '26 Bis Rue de la Baume, Paris', '+33145678900');



-- Insertion des types de démarches
INSERT INTO demande (nom,libelle, type, description, delai_moyen) VALUES
                                                                          ('Passeport','PASSEPORT', 'NOUVELLE', 'Délivrance d’un passeport biométrique.',  '30 jours'),
                                                                          ('Carte Nationale d’Identité','CNI', 'NOUVELLE', 'Obtention d’une CNI pour les citoyens gabonais.',  '10 jours'),
                                                                          ('Permis de Conduire','PERMIS_CONDUIRE', 'RENOUVELLEMENT', 'Renouvellement du permis de conduire après expiration.',  '7 jours'),
                                                                          ('Extrait de Naissance','EXTRAIT_NAISSANCE', 'DUPLICATA', 'Délivrance d’un duplicata de l’acte de naissance.',  '5 jours'),
                                                                          ('Passeport','PASSEPORT', 'RENOUVELLEMENT', 'Renouvellement du passeport biométrique.',  '15 jours');


INSERT INTO demande_centre (demande_id, centre_id) VALUES
                                                       (1, 1), -- Passeport -> DGDI
                                                       (2, 1), -- Carte d'identité -> DGDI
                                                       (3, 2), -- Permis de conduire -> Mairie d’Owendo
                                                       (4, 1), -- Extrait de naissance -> Préfecture de Libreville
                                                       (5, 1); -- Passeport renouvellement -> DGDI


-- Insertion des documents requis pour chaque démarche
INSERT INTO document_requis (nom, demande_id, obligatoire) VALUES
                                                               ('Le formulaire de demande de passeport remplit', 1, true), -- Passeport
                                                               ('1 photo couleur récente', 1, true), -- Passeport
                                                               ('Acte de naissance original ainsi que le décret et le certificat de nationalité pour les naturalisés', 1, true), -- Passeport
                                                               ('Une autorisation parentale pour les moins de 16 ans et copie de la pièce d''identité du parent signataire de l''autorisation', 1, true), -- Passeport
                                                               ('Tout justificatif relatif à un éventuel changement de nom ou d''identité', 1, true), -- Passeport
                                                               ('Ancien passeport', 3, true), -- Renouvellement de permis
                                                               ('Carte d’identité', 4, true), -- Duplicata d’extrait de naissance
--                                                              passport renouvelement
                                                               ('Ancien passeport', 5, true), -- Duplicata d’extrait de naissance
                                                               ('Acte de naissance', 5, true), -- Duplicata d’extrait de naissance
                                                               ('Une lettre de procuration', 5, true), -- Duplicata d’extrait de naissance
                                                               ('Preuve de non résidence au Gabon', 5, true), -- Duplicata d’extrait de naissance
                                                               ('Tout autre document nécessaire', 5, true), -- Duplicata d’extrait de naissance
--                                                                CNI
                                                               ('Attestation NIP (Format PDF)', 2, true), -- Duplicata d’extrait de naissance
                                                               ('Acte de naissance / Décret / Jugements', 2, true), -- Duplicata d’extrait de naissance
                                                               ('Copie de passeport', 2, true), -- Duplicata d’extrait de naissance
                                                               ('Acte de mariage pour les femmes mariées', 2, true); -- Duplicata d’extrait de naissance


INSERT INTO etape (ordre, titre, details, demande_id) VALUES
                                                          (1, 'Remplir le formulaire', 'Téléchargez et complétez le formulaire en ligne sur www.dgdi.ga ou sur place.', 1),
                                                          (2, 'Rassembler les documents', 'Joindre l’acte de naissance, justificatif de domicile et photos.', 1),
                                                          (3, 'Déposer le dossier', 'Se rendre à la préfecture avec les documents nécessaires.', 1),
                                                          (4, 'Paiement des frais', 'Payer les frais de dossier au guichet.', 1),
                                                          (5, 'Retrait du passeport', 'Revenir après 15 jours pour récupérer le passeport.', 1),
--                                                         passport renouvelement
                                                          (1, 'Vérification de votre titre expiré', 'Vous devez fournir les informations exactes sur votre ancien passeport à renouveler.', 5),
                                                          (2, 'Saisie des informations personnelles', 'Vous devrez correctement remplir, en ligne, le formulaire de demande de renouvellement.', 5),
                                                          (3, 'Photo', 'Dans cette étape vous devrez fournir votre photo au format JPEG ou PNG et respectant les normes ICAO.', 5),
                                                          (4, 'Ajout des documents justificatifs', 'Les documents justificatifs à fournir sont : Ancien passeport ; Acte de naissance ; Une lettre de procuration ; Preuve de non résidence au Gabon ; Tout autre document nécessaire', 5),
                                                          (5, 'Paiement et validation', 'L’option de paiement sur place est disponible', 5),
--                                                          CNIE
                                                          (1, 'Vérification de votre titre', 'Vous devez fournir les informations exactes de votre Passeport ou CNI électronique.', 2),
                                                          (2, 'Saisie des informations personnelles', 'Vous devrez correctement remplir, en ligne, le formulaire de demande de CNIE.', 2),
                                                          (3, 'Photo d’identité', 'Dans cette étape vous devrez fournir votre photo au format JPEG ou PNG et respectant les normes', 2),
                                                          (4, 'Documents à scanner', 'Les documents justificatifs à fournir', 2),
                                                          (5, 'Code PIN et remise', 'Votre CNI électronique sera protégée par un code PIN confidentiel, et récupérée au lieu de remise de votre choix.', 2),
                                                          (6, 'Synthèse et validation', 'Une synthèse de toutes les données fournies.', 2);



INSERT INTO cout (type, montant, demande_id) VALUES
                                                 ('Frais de dossier pour les adultes', 40000, 1), -- Passeport
                                                 ('Frais de dossier pour les moins de 16 ans', 30000, 1), -- Passeport
                                                 ('Une quittance du trésor public', 5000, 1), -- Passeport
                                                 ('Frais de dossier', 45000, 5), -- Passeport
                                                 ('Frais de duplicata', 3000, 4), -- Extrait de naissance
                                                 ('Frais de renouvellement', 20000, 3); -- Permis de conduire
